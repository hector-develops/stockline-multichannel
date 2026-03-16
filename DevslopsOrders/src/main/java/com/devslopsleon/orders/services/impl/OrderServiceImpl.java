package com.devslopsleon.orders.services.impl;

import com.devslopsleon.orders.core.dto.transactional.anymarket.OrderAnyDTO;
import com.devslopsleon.orders.core.dto.transactional.order.*;
import com.devslopsleon.orders.core.models.address.DeliveryAddress;
import com.devslopsleon.orders.core.models.company.*;
import com.devslopsleon.orders.core.models.enums.DeliveryStatus;
import com.devslopsleon.orders.core.models.enums.ShipmentStatus;
import com.devslopsleon.orders.core.models.enums.TransactionStatus;
import com.devslopsleon.orders.core.models.ordersm.Order;
import com.devslopsleon.orders.core.models.ordersm.OrderEntry;
import com.devslopsleon.orders.core.models.person.Customer;
import com.devslopsleon.orders.core.models.person.User;
import com.devslopsleon.orders.core.models.types.mov.DeliveryMode;
import com.devslopsleon.orders.core.models.types.stts.DeliveryStatusClass;
import com.devslopsleon.orders.core.repository.*;
import com.devslopsleon.orders.dexeptions.BadRequestBusinessException;
import com.devslopsleon.orders.dexeptions.CompanyNotFoundException;
import com.devslopsleon.orders.dexeptions.ProductNotFoundException;
import com.devslopsleon.orders.dexeptions.StockUnavailableException;
import com.devslopsleon.orders.mappers.order.OrderMapper;
import com.devslopsleon.orders.services.IOrderService;
import com.devslopsleon.orders.services.auth.TenantContext;
import com.devslopsleon.orders.services.helpers.OrderStockLineHelper;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderServiceImpl implements IOrderService {

    private final StockLevelRepository stockLevelRepository;
    private final CompanyRepository companyRepository;
    private final OrderEntryRepository orderEntryRepository;
    private final OrderRepository orderRepository;
    private final DeliveryStatusRepository deliveryStatusRepository;
    private final WarehouseRepository warehouseRepository;
    private final UserRepository userRepository;
    private final DeliveryModeRepository deliveryModeRepository;
    private final CustomerRepository customerRepository;
    private final OrderStockLineHelper orderStockLineHelper;
    private final ShippingPolicyRepository shippingPolicyRepository;
    final String NO_DELIVERED = "NOT_DELIVERED";
    final String NOT_SHIPPED = "NOT_SHIPPED";
    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public OrderDTO createOrder(final OrderRequestDTO order, final String warehouse) {

        //Get id company by Tenant
        Long companyPk = TenantContext.getCompanyPk();
        if (companyPk == null) {
            throw new RuntimeException("Tenant not resolved"); // o AccessDenied
        }
        Order ord = new Order();

        // 1) Validar policy por codes
        ShippingPolicy policy = orderStockLineHelper.resolveAndValidatePolicy(order, companyPk);

        // 2) deliveryMan si aplica
        User deliveryMan = orderStockLineHelper.resolveDeliveryManIfRequired(policy, order, companyPk);

        //ord.setShippingPolicy(policy);        // ✅ fuente de verdad
        //ord.setDeliveryMan(deliveryMan);      // ✅ null si no aplica


        Optional<Company> company = companyRepository.findCompanyById(companyPk);
        if(company.isEmpty()){
            throw new CompanyNotFoundException("Company Not found or not exist: ");
        }
        //find and validate if channel exist
        SalesChannel salesChannel = orderStockLineHelper.findAndValidateExistSalesChannel(order.getChannel(), companyPk);
        // find and validate if warehouse exist.
        Optional<Warehouse> wh = warehouseRepository.findWarehouseByCompanyAndCodeAndStatus(warehouse, companyPk);
        if(wh.isEmpty()){
            throw new RuntimeException("Warehouse not found or not exist: " + warehouse);
        }
        //find user to validate if user exist
        Optional<User> user = userRepository.findUserByUsernameAndCompanyAndStatus(order.getUsername(), companyPk);
        if(user.isEmpty()){
            throw new RuntimeException("User not found or not exist: " + order.getUsername());
        }
        //find deliveryMode or use default
        Optional<DeliveryMode> dm = deliveryModeRepository.findDeliveryModeByCode(order.getDeliveryMode(), companyPk);
        if(dm.isEmpty()){
            throw new RuntimeException("DeliveryMode not found or not exist: " + order.getDeliveryMode());
        }
        Optional<Customer> customer = customerRepository.findCustomerByCompanyAndEmail(order.getCustomer().getMail(), companyPk);
        if(customer.isEmpty()){
            throw new RuntimeException("Customer not found or not exist: " + order.getCustomer().getMail());
        }
        //TODO set DATA to create order

        ord.setCompany(company.get());
        ord.setWarehouseId(wh.get().getPk());
        ord.setCustomer(customer.get());
        ord.setCreatedBy(user.get());
        ord.setDeliveryMode(dm.get());
        ord.setSalesChannel(salesChannel);
        ord.setShipmentStatus(ShipmentStatus.NOT_SHIPPED);
        ord.setDeliveryStatus(DeliveryStatus.NOT_DELIVERED);
        DeliveryAddress da =  orderStockLineHelper.buildDeliveryAddressSnapshot(order, customer.get(), companyPk);
        ord.setDeliveryAddress(da);
        ord.setComments(order.getComments());
        ord.setAmount(order.getAmount());
        ord.setQuantity(order.getTotal());
        ord.setCreationTime(LocalDateTime.now());
        ord.setModifiedTime(LocalDateTime.now());

        orderRepository.save(ord);

        //TODO for to read all entries
        double totalAmount = 0.0;
        int totalQty = 0;
        int renglon = 1;

        for(OrderEntryRequestDTO e : order.getEntries()){
            int qty = e.getQuantity();
            if (qty <= 0) {
                throw new RuntimeException("Quantity must be > 0 for product: " + e.getProduct());
            }
            StockLevel stock = stockLevelRepository
                    .findStockByproductAndWarehouseId(e.getProduct(), wh.get().getPk(), companyPk)
                    .orElseThrow(() -> new ProductNotFoundException("Stock not found for product: " + e.getProduct()));

            // ✅ Regla recomendada: available = disponible para vender
            if (stock.getAvailable() < qty) {
                throw new StockUnavailableException("Insufficient stock for product: " + e.getProduct());
            }
            // ✅ Reserva correcta
            stock.setAvailable(stock.getAvailable() - qty);
            stock.setReserved((stock.getReserved() == null ? 0 : stock.getReserved()) + qty);

            // ✅ Dispara optimistic lock al guardar
            stockLevelRepository.save(stock);
            // 3) Guardar entry
            OrderEntry entry = new OrderEntry();
            entry.setOrder(ord);
            entry.setProductCode(e.getProduct());
            entry.setQuantity(qty); // ideal cambiar a Integer en entidad
            entry.setPrice(e.getPrice());
            entry.setExternalPrice(0.0);
            double lineAmount = (e.getAmount() != null) ? e.getAmount() : (e.getPrice() * qty);
            entry.setAmount(lineAmount);
            entry.setCreationTime(LocalDateTime.now());
            entry.setModifiedTime(LocalDateTime.now());
            entry.setRenglon(renglon);
            orderEntryRepository.save(entry);
            renglon++;
            totalQty += qty;
            totalAmount += lineAmount;

        }
        // 4) Totales
        ord.setQuantity(BigDecimal.valueOf(totalQty));
        ord.setAmount(BigDecimal.valueOf(totalAmount));
        orderRepository.save(ord);
        return null;
    }

    @Override
    @Transactional
    public void cancelOrder(OrderOptionsDTO order, Long orderId) {
        Long companyPk = TenantContext.getCompanyPk();
        if (companyPk == null) {
            throw new RuntimeException("Tenant not resolved"); // o AccessDenied
        }
        //find Company to validate if exist and status is active
        Optional<Company> company = companyRepository.findCompanyById(companyPk);
        if(company.isEmpty()){
            throw new CompanyNotFoundException("Company Not found or not exist: ");
        }
        List<Order> orders = orderRepository.findOrdersByIdAndCompany(company.get().getPk(), orderId);
        if(orders.size() != 1){
            throw new RuntimeException("Order not found");
        }
        Order orderFounded = orders.getFirst();
        // 1) Validaciones
        if (orderFounded.getShipmentStatus() == ShipmentStatus.SHIPPED) {
            throw new RuntimeException("Order already shipped, cannot cancel");
        }
        if (orderFounded.getShipments() != null && !orderFounded.getShipments().isEmpty()) {
            throw new RuntimeException("Order has shipments, cannot cancel");
        }
        if (Objects.equals(orderFounded.getTransactionStatus().getCode(), TransactionStatus.CANCELLED.toString())) {
            // idempotente: regresa como OK
            //orderMapper.toDTO(ord);
            return;
        }
        Long warehouseId = orderFounded.getWarehouseId();
        if (warehouseId == null) {
            throw new RuntimeException("Order missing warehouseId");
        }
        Long companyIdOrd = orderFounded.getCompany().getPk();
        // 2) release stock
        for (OrderEntry entry : orderFounded.getEntries()) {

            String productCode = entry.getProductCode();
            int qty = entry.getQuantity(); // si es Integer; si es Double, castea con cuidado

            StockLevel stock = stockLevelRepository
                    .findStockByproductAndWarehouseId(productCode, warehouseId, companyIdOrd)
                    .orElseThrow(() -> new RuntimeException("Stock not found for product: " + productCode));

            int reserved = stock.getReserved();   // ya debe ser NOT NULL default 0
            int available = stock.getAvailable();

            if (reserved < qty) {
                throw new RuntimeException("Reserved stock inconsistency for product: " + productCode);
            }

            stock.setReserved(reserved - qty);
            stock.setAvailable(available + qty);

            stockLevelRepository.save(stock); // optimistic lock
        }
        // 3) update order
        orderFounded.setDeliveryStatus(DeliveryStatus.CANCELLED);
        orderFounded.setModifiedTime(LocalDateTime.now());
        orderRepository.save(orderFounded);
    }

    @Override
    public Page<OrderDTO> listOrdersPageable(LocalDate from, LocalDate to, Long orderId, Pageable pageable) {
        Long companyPk = TenantContext.getCompanyPk();
        if (companyPk == null) {
            throw new RuntimeException("Tenant not resolved");
        }
        // Si viene orderId, prioriza búsqueda por ID
        if (orderId != null) {
            return orderRepository.findByPkAndCompanyPk(orderId, companyPk)
                    .map(orderMapper::toDTO)
                    .map(dto -> new PageImpl<>(java.util.List.of(dto), pageable, 1))
                    .orElseGet(() -> new PageImpl<>(java.util.List.of(), pageable, 0));
        }
        // Rango de fechas: si to viene, lo hacemos inclusivo
        LocalDateTime fromDt = (from != null)
                ? from.atStartOfDay()
                : LocalDate.now().minusDays(30).atStartOfDay();

        LocalDateTime toDt = (to != null)
                ? to.plusDays(1).atStartOfDay()
                : LocalDate.now().plusDays(1).atStartOfDay();

        return orderRepository.findByCompanyAndCreationTimeBetween(companyPk, fromDt, toDt, pageable)
                .map(orderMapper::toDTO);
    }

    @Override
    public List<OrderDTO> listOrdersByCompany() {
        Long companyPk = TenantContext.getCompanyPk();
        if (companyPk == null) {
            throw new RuntimeException("Tenant not resolved"); // o AccessDenied
        }
        List<Order> orders = orderRepository.findOrdersByCompanyAll(companyPk);
        return List.of();
    }

    @Override
    public OrderDTO findOrderById(Long id) {
        return null;
    }

    @Override
    public boolean deleteOrder(Long id) {
        return false;
    }

    @Override
    public String createOrderAnymarket(final OrderAnyDTO orderAnyDTO) {

        //TODO map anyOrderDTO To Model
        System.out.println("Si paso");
        return "";
    }

    private DeliveryStatusClass findDeliveryStatus(final String deliveryCode){
        Optional<DeliveryStatusClass> ds = deliveryStatusRepository.findDeliveryStatusByCode(deliveryCode);
        return ds.orElse(null);
    }







    public OrderServiceImpl(StockLevelRepository stockLevelRepository, CompanyRepository companyRepository, OrderEntryRepository orderEntryRepository, OrderRepository orderRepository, DeliveryStatusRepository deliveryStatusRepository, WarehouseRepository warehouseRepository, UserRepository userRepository, DeliveryModeRepository deliveryModeRepository, CustomerRepository customerRepository, OrderStockLineHelper stockLineHelper, OrderStockLineHelper orderStockLineHelper, ShippingPolicyRepository shippingPolicyRepository, OrderMapper orderMapper) {
        this.stockLevelRepository = stockLevelRepository;
        this.companyRepository = companyRepository;
        this.orderEntryRepository = orderEntryRepository;
        this.orderRepository = orderRepository;
        this.deliveryStatusRepository = deliveryStatusRepository;
        this.warehouseRepository = warehouseRepository;
        this.userRepository = userRepository;
        this.deliveryModeRepository = deliveryModeRepository;
        this.customerRepository = customerRepository;
        this.orderStockLineHelper = orderStockLineHelper;
        this.shippingPolicyRepository = shippingPolicyRepository;
        this.orderMapper = orderMapper;
    }
}
