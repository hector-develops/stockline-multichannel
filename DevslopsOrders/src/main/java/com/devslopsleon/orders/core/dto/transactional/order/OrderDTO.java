package com.devslopsleon.orders.core.dto.transactional.order;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.devslopsleon.orders.core.dto.WarehouseDTO;
import com.devslopsleon.orders.core.dto.address.DeliveryAddressDTO;
import com.devslopsleon.orders.core.dto.company.ShipmentInfoDTO;
import com.devslopsleon.orders.core.dto.transactional.payment.PaymentInfoDTO;
import com.devslopsleon.orders.core.dto.user.CustomerDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Setter
@Getter
public class OrderDTO implements Serializable {

    private Long id;
    private String company;
    private String deliveryStatus;
    private String orderStatus;
    private String paymentStatus;
    private Double quantity;
    private Double total;
    private Double shippingCost;
    private int channel;
    private String channelName;
    private WarehouseDTO warehouse;
    private DeliveryAddressDTO deliveryAddress;
    private CustomerDTO customer;
    private String createdBy;
    private String comments;
    private List<PaymentInfoDTO> payments;
    private List<ShipmentInfoDTO> shipments;
    private List<OrderEntryDTO> entries;
    private LocalDateTime creationTime;
    private LocalDateTime modifiedTime;
    private String integrationId;
    private String uniqueId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(Double shippingCost) {
        this.shippingCost = shippingCost;
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

    public WarehouseDTO getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(WarehouseDTO warehouse) {
        this.warehouse = warehouse;
    }

    public DeliveryAddressDTO getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddressDTO deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<PaymentInfoDTO> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentInfoDTO> payments) {
        this.payments = payments;
    }

    public List<ShipmentInfoDTO> getShipments() {
        return shipments;
    }

    public void setShipments(List<ShipmentInfoDTO> shipments) {
        this.shipments = shipments;
    }

    public List<OrderEntryDTO> getEntries() {
        return entries;
    }

    public void setEntries(List<OrderEntryDTO> entries) {
        this.entries = entries;
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

    public String getIntegrationId() {
        return integrationId;
    }

    public void setIntegrationId(String integrationId) {
        this.integrationId = integrationId;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }
}
