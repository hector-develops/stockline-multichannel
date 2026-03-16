package com.devslopsleon.orders.services.helpers;


import com.devslopsleon.orders.core.dto.transactional.order.OrderRequestDTO;
import com.devslopsleon.orders.core.models.address.Address;
import com.devslopsleon.orders.core.models.address.DeliveryAddress;
import com.devslopsleon.orders.core.models.company.SalesChannel;
import com.devslopsleon.orders.core.models.company.ShippingPolicy;
import com.devslopsleon.orders.core.models.person.Customer;
import com.devslopsleon.orders.core.models.person.User;
import com.devslopsleon.orders.core.repository.SalesChannelRepository;
import com.devslopsleon.orders.core.repository.ShippingPolicyRepository;
import com.devslopsleon.orders.core.repository.UserRepository;
import com.devslopsleon.orders.dexeptions.BadRequestBusinessException;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Optional;

@Component
public class OrderStockLineHelper {

    private final SalesChannelRepository salesChannelRepository;
    private final ShippingPolicyRepository shippingPolicyRepository;
    private final UserRepository userRepository;

    public OrderStockLineHelper(SalesChannelRepository salesChannelRepository, ShippingPolicyRepository shippingPolicyRepository, UserRepository userRepository) {
        this.salesChannelRepository = salesChannelRepository;
        this.shippingPolicyRepository = shippingPolicyRepository;
        this.userRepository = userRepository;
    }

    public DeliveryAddress buildDeliveryAddressSnapshot(final OrderRequestDTO req, final Customer customer, final Long companyId) {
        DeliveryAddress addr = new DeliveryAddress();

        if(req.isUseCustomerAddress()){
            Address cAddr = customer.getAddress();
            if (cAddr == null) throw new RuntimeException("Customer has no address");
            addr.setStreet(cAddr.getStreet());
            addr.setNumber(cAddr.getNumber());
            addr.setTown(cAddr.getTown());
            addr.setZipcode(cAddr.getZipcode());
            addr.setPhone(cAddr.getPhone());
            addr.setNeighborhood(cAddr.getNeighborhood());
            addr.setDeliveryInstructions(cAddr.getDeliveryInstructions());
        } else {
            var d = req.getDeliveryAddress();
            if (d == null) throw new RuntimeException("DeliveryAddress is required when useCustomerAddress=false");
            addr.setStreet(d.getStreet());
            addr.setNumber(String.valueOf(d.getNumber()));
            addr.setTown(d.getTown());
            addr.setZipcode(d.getZipcode());
            addr.setPhone(d.getPhone());
            addr.setNeighborhood(d.getNeighborhood());
            addr.setDeliveryInstructions(d.getDeliveryInstructions());

        }
        addr.setCompanyId(companyId);
        return addr;
    }

    public SalesChannel findAndValidateExistSalesChannel(final String channel, final Long companyId){
        Optional<SalesChannel> scFound = salesChannelRepository.findSalesChannelByCompanyAndCode(channel, companyId);
        if(scFound.isEmpty()){
            throw new RuntimeException("Sales channel not founded");
        }
        return scFound.get();
    }

    public ShippingPolicy resolveAndValidatePolicy(OrderRequestDTO req, Long companyPk) {

        String sc = norm(req.getSalesChannelCode());
        String dm = norm(req.getDeliveryModeCode());
        String sm = norm(req.getShippingMethodCode());
        String cc = norm(req.getCarrierCode());

        ShippingPolicy policy = shippingPolicyRepository.findValidPolicy(companyPk, sc, dm, sm, cc)
                .orElseThrow(() -> new BadRequestBusinessException(
                        "INVALID_SHIPPING_CONFIGURATION: " + sc + "/" + dm + "/" + sm + "/" + cc
                ));

        String deliveryManMail = req.getDeliveryManMail();
        boolean hasDeliveryMan = deliveryManMail != null && !deliveryManMail.isBlank();

        // ✅ Validación estricta como pediste
        if (policy.isRequiresDeliveryMan() && !hasDeliveryMan) {
            throw new BadRequestBusinessException("DELIVERY_MAN_REQUIRED");
        }
        if (!policy.isRequiresDeliveryMan() && hasDeliveryMan) {
            throw new BadRequestBusinessException("DELIVERY_MAN_NOT_ALLOWED");
        }

        return policy;
    }

    private String norm(String s) {
        if (s == null) return null;
        return s.trim().toUpperCase(Locale.ROOT);
    }

    private String normalize(String s) {
        return (s == null) ? null : s.trim().toUpperCase(Locale.ROOT);
    }

    public User resolveDeliveryManIfRequired(ShippingPolicy policy, OrderRequestDTO req, Long companyPk) {
        if (!policy.isRequiresDeliveryMan()) return null;

        String mail = req.getDeliveryManMail().trim().toLowerCase(Locale.ROOT);

        User u = userRepository.findUserByMailAndCompanyAndStatus(mail, companyPk)
                .orElseThrow(() -> new BadRequestBusinessException("DELIVERY_MAN_NOT_FOUND"));

        String role = u.getRole().getCode();
        if (!"DELIVERY_MAN".equalsIgnoreCase(role)) {
            throw new BadRequestBusinessException("INVALID_DELIVERY_MAN_ROLE");
        }
        return u;
    }


}
