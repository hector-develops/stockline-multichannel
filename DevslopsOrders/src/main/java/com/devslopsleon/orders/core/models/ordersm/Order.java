package com.devslopsleon.orders.core.models.ordersm;


import com.devslopsleon.orders.core.models.address.DeliveryAddress;
import com.devslopsleon.orders.core.models.company.*;
import com.devslopsleon.orders.core.models.enums.DeliveryStatus;
import com.devslopsleon.orders.core.models.enums.ShipmentStatus;
import com.devslopsleon.orders.core.models.person.Customer;
import com.devslopsleon.orders.core.models.person.User;
import com.devslopsleon.orders.core.models.types.mov.DeliveryMode;
import com.devslopsleon.orders.core.models.types.stts.PaymentStatus;
import com.devslopsleon.orders.core.models.types.stts.TransactionStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK")
    private Long pk;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="COMPANY", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Company company;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TRANSACTIONSTATUS")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private TransactionStatus transactionStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "DELIVERYSTATUS", nullable = false)
    private DeliveryStatus deliveryStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "SHIPMENTSTATUS", nullable = false)
    private ShipmentStatus shipmentStatus;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="PAYMENTSTATUS")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private PaymentStatus paymentStatus;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true)
    @JsonManagedReference
    private List<OrderEntry> entries = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true)
    @JsonManagedReference
    private List<ShipmentInfo> shipments = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true)
    @JsonManagedReference
    private List<PaymentInfo> payments = new ArrayList<>();

    @Column(name = "QUANTITY")
    private BigDecimal quantity;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "SHIPPINGCOST")
    private Double shippingCost;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "CURRENCY")
    private String currency;

    @Column(name = "CHANNEL")
    private int channel;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SALESCHANNEL", nullable = false)
    private SalesChannel salesChannel;

    @Column(name = "CHANNELNAME")
    private String channelName;

    @Column(name = "INTEGRATORID")
    private String integratorAccountID;

    @Column(name = "INTEGRATIONID")
    private String integrationId;

    @Column(name = "CARTID")
    private String cartId;

    @Column(name = "UNIQUEEXTERNALID")
    private String uniqueExternalId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WAREHOUSE")
    private Warehouse warehouse;

    @Column(name = "WAREHOUSEID")
    private Long warehouseId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CREATED_BY", nullable = false)
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER")
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    @JoinColumn(name = "DELIVERY_ADDRESS", nullable = false)
    private DeliveryAddress deliveryAddress;

    @Column(name = "CREATIONTIME")
    private LocalDateTime creationTime;

    @Column(name = "MODIFIEDTIME")
    private LocalDateTime modifiedTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DELIVERYMODE", nullable = false)
    private DeliveryMode deliveryMode;

    /*
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="shipping_policy", nullable=false)
    private ShippingPolicy shippingPolicy;

     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="DELIVERY_MAN")
    private User deliveryMan; // solo si aplica (SELF_COMPANY)

    @PrePersist
    protected void onCreate() {
        creationTime = LocalDateTime.now();
        modifiedTime = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        modifiedTime = LocalDateTime.now();
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public List<OrderEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<OrderEntry> entries) {
        this.entries = entries;
    }

    public List<ShipmentInfo> getShipments() {
        return shipments;
    }

    public void setShipments(List<ShipmentInfo> shipments) {
        this.shipments = shipments;
    }

    public List<PaymentInfo> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentInfo> payments) {
        this.payments = payments;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(Double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getIntegratorAccountID() {
        return integratorAccountID;
    }

    public void setIntegratorAccountID(String integratorAccountID) {
        this.integratorAccountID = integratorAccountID;
    }

    public String getIntegrationId() {
        return integrationId;
    }

    public void setIntegrationId(String integrationId) {
        this.integrationId = integrationId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getUniqueExternalId() {
        return uniqueExternalId;
    }

    public void setUniqueExternalId(String uniqueExternalId) {
        this.uniqueExternalId = uniqueExternalId;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public DeliveryMode getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(DeliveryMode deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    public ShipmentStatus getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(ShipmentStatus shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    public SalesChannel getSalesChannel() {
        return salesChannel;
    }

    public void setSalesChannel(SalesChannel salesChannel) {
        this.salesChannel = salesChannel;
    }

    public User getDeliveryMan() {
        return deliveryMan;
    }

    public void setDeliveryMan(User deliveryMan) {
        this.deliveryMan = deliveryMan;
    }
}
