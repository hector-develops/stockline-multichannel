package com.devslopsleon.orders.core.dto.transactional.producteca;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class OrderPtecaDTO implements Serializable {

    private Long pk;
    private String ChannelName;
    private int Channel;
    private ContactDTO Contact;
    private int ProductecaAccount;
    private int Id;
    private boolean IsOpen;
    private Long CustomId;
    private String Currency;
    private boolean HasAnyShipments;
    private boolean HasAnyPayments;
    private List<OrderEntryPtecaDTO> lines;
    private String Mode;
    private BigDecimal Amount;
    private BigDecimal ShippingCost;
    private BigDecimal FinancialCost;
    private BigDecimal PaidApproved;
    private String PaymentStatus;
    private String DeliveryStatus;
    private String PaymentFulfillmentStatus;
    private String DeliveryFulfillmentStatus;
    private String DeliveryMethod;
    private String PaymentTerm;
    private boolean IsCanceled;
    private String Notes;
    private String Warehouse;
    private String WarehouseId;
    private WarehouseIntegrationDTO WarehouseIntegration;
    private InvoiceIntegrationDTO InvoiceIntegration;
    private List<OrderEntryPtecaDTO> Lines;
    private List<PaymentPtecaDTO> Payments;
    private List<ShipmentsPtecaDTO> Shipments;


    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public String getChannelName() {
        return ChannelName;
    }

    public void setChannelName(String channelName) {
        ChannelName = channelName;
    }

    public int getChannel() {
        return Channel;
    }

    public void setChannel(int channel) {
        Channel = channel;
    }

    public ContactDTO getContact() {
        return Contact;
    }

    public void setContact(ContactDTO contact) {
        Contact = contact;
    }

    public int getProductecaAccount() {
        return ProductecaAccount;
    }

    public void setProductecaAccount(int productecaAccount) {
        ProductecaAccount = productecaAccount;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public boolean isOpen() {
        return IsOpen;
    }

    public void setOpen(boolean open) {
        IsOpen = open;
    }

    public Long getCustomId() {
        return CustomId;
    }

    public void setCustomId(Long customId) {
        CustomId = customId;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

    public boolean isHasAnyShipments() {
        return HasAnyShipments;
    }

    public void setHasAnyShipments(boolean hasAnyShipments) {
        HasAnyShipments = hasAnyShipments;
    }

    public boolean isHasAnyPayments() {
        return HasAnyPayments;
    }

    public void setHasAnyPayments(boolean hasAnyPayments) {
        HasAnyPayments = hasAnyPayments;
    }

    public List<OrderEntryPtecaDTO> getLines() {
        return lines;
    }

    public void setLines(List<OrderEntryPtecaDTO> lines) {
        this.lines = lines;
    }

    public List<PaymentPtecaDTO> getPayments() {
        return Payments;
    }

    public void setPayments(List<PaymentPtecaDTO> payments) {
        Payments = payments;
    }

    public List<ShipmentsPtecaDTO> getShipments() {
        return Shipments;
    }

    public void setShipments(List<ShipmentsPtecaDTO> shipments) {
        Shipments = shipments;
    }

    public String getMode() {
        return Mode;
    }

    public void setMode(String mode) {
        Mode = mode;
    }

    public BigDecimal getAmount() {
        return Amount;
    }

    public void setAmount(BigDecimal amount) {
        Amount = amount;
    }

    public BigDecimal getShippingCost() {
        return ShippingCost;
    }

    public void setShippingCost(BigDecimal shippingCost) {
        ShippingCost = shippingCost;
    }

    public BigDecimal getFinancialCost() {
        return FinancialCost;
    }

    public void setFinancialCost(BigDecimal financialCost) {
        FinancialCost = financialCost;
    }

    public BigDecimal getPaidApproved() {
        return PaidApproved;
    }

    public void setPaidApproved(BigDecimal paidApproved) {
        PaidApproved = paidApproved;
    }

    public String getPaymentStatus() {
        return PaymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        PaymentStatus = paymentStatus;
    }

    public String getDeliveryStatus() {
        return DeliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        DeliveryStatus = deliveryStatus;
    }

    public String getPaymentFulfillmentStatus() {
        return PaymentFulfillmentStatus;
    }

    public void setPaymentFulfillmentStatus(String paymentFulfillmentStatus) {
        PaymentFulfillmentStatus = paymentFulfillmentStatus;
    }

    public String getDeliveryFulfillmentStatus() {
        return DeliveryFulfillmentStatus;
    }

    public void setDeliveryFulfillmentStatus(String deliveryFulfillmentStatus) {
        DeliveryFulfillmentStatus = deliveryFulfillmentStatus;
    }

    public String getDeliveryMethod() {
        return DeliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        DeliveryMethod = deliveryMethod;
    }

    public String getPaymentTerm() {
        return PaymentTerm;
    }

    public void setPaymentTerm(String paymentTerm) {
        PaymentTerm = paymentTerm;
    }

    public boolean isCanceled() {
        return IsCanceled;
    }

    public void setCanceled(boolean canceled) {
        IsCanceled = canceled;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public String getWarehouse() {
        return Warehouse;
    }

    public void setWarehouse(String warehouse) {
        Warehouse = warehouse;
    }

    public String getWarehouseId() {
        return WarehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        WarehouseId = warehouseId;
    }

    public WarehouseIntegrationDTO getWarehouseIntegration() {
        return WarehouseIntegration;
    }

    public void setWarehouseIntegration(WarehouseIntegrationDTO warehouseIntegration) {
        WarehouseIntegration = warehouseIntegration;
    }

    public InvoiceIntegrationDTO getInvoiceIntegration() {
        return InvoiceIntegration;
    }

    public void setInvoiceIntegration(InvoiceIntegrationDTO invoiceIntegration) {
        InvoiceIntegration = invoiceIntegration;
    }
}
