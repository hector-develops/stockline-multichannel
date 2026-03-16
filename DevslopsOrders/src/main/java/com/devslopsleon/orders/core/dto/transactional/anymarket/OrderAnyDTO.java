package com.devslopsleon.orders.core.dto.transactional.anymarket;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OrderAnyDTO implements Serializable {

    private Long id;
    private String accountName;
    private String marketPlaceId;
    private String marketPlaceNumber;
    private String marketPlace;
    private Date createdAt;
    private Date paymentDate;
    private String transmissionStatus;
    private String status;
    private String marketPlaceStatus;
    private Double discount;
    private Double freight;
    private Double sellerFreight;
    private Double interestValue;
    private Double gross;
    private Double total;
    private ShippingAnyDTO shipping;
    private AddressAnyDTO anymarketAddress;
    private BuyerAnyDTO buyer;
    private List<PaymentAnyDTO> payments;
    private List<OrderItemDTO> items;
    private String deliveryStatus;
    private Long idAccount;
    private boolean fulfillment;
    private MetadataAnyDTO metadata;
    private String orderTypeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getMarketPlaceId() {
        return marketPlaceId;
    }

    public void setMarketPlaceId(String marketPlaceId) {
        this.marketPlaceId = marketPlaceId;
    }

    public String getMarketPlaceNumber() {
        return marketPlaceNumber;
    }

    public void setMarketPlaceNumber(String marketPlaceNumber) {
        this.marketPlaceNumber = marketPlaceNumber;
    }

    public String getMarketPlace() {
        return marketPlace;
    }

    public void setMarketPlace(String marketPlace) {
        this.marketPlace = marketPlace;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getTransmissionStatus() {
        return transmissionStatus;
    }

    public void setTransmissionStatus(String transmissionStatus) {
        this.transmissionStatus = transmissionStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMarketPlaceStatus() {
        return marketPlaceStatus;
    }

    public void setMarketPlaceStatus(String marketPlaceStatus) {
        this.marketPlaceStatus = marketPlaceStatus;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }

    public Double getSellerFreight() {
        return sellerFreight;
    }

    public void setSellerFreight(Double sellerFreight) {
        this.sellerFreight = sellerFreight;
    }

    public Double getInterestValue() {
        return interestValue;
    }

    public void setInterestValue(Double interestValue) {
        this.interestValue = interestValue;
    }

    public Double getGross() {
        return gross;
    }

    public void setGross(Double gross) {
        this.gross = gross;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public ShippingAnyDTO getShipping() {
        return shipping;
    }

    public void setShipping(ShippingAnyDTO shipping) {
        this.shipping = shipping;
    }

    public AddressAnyDTO getAnymarketAddress() {
        return anymarketAddress;
    }

    public void setAnymarketAddress(AddressAnyDTO anymarketAddress) {
        this.anymarketAddress = anymarketAddress;
    }

    public BuyerAnyDTO getBuyer() {
        return buyer;
    }

    public void setBuyer(BuyerAnyDTO buyer) {
        this.buyer = buyer;
    }

    public List<PaymentAnyDTO> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentAnyDTO> payments) {
        this.payments = payments;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Long idAccount) {
        this.idAccount = idAccount;
    }

    public boolean isFulfillment() {
        return fulfillment;
    }

    public void setFulfillment(boolean fulfillment) {
        this.fulfillment = fulfillment;
    }

    public MetadataAnyDTO getMetadata() {
        return metadata;
    }

    public void setMetadata(MetadataAnyDTO metadata) {
        this.metadata = metadata;
    }

    public String getOrderTypeName() {
        return orderTypeName;
    }

    public void setOrderTypeName(String orderTypeName) {
        this.orderTypeName = orderTypeName;
    }
}
