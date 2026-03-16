package com.devslopsleon.orders.core.dto.transactional.order;


import com.devslopsleon.orders.core.dto.address.DeliveryAddressRequestDTO;
import com.devslopsleon.orders.core.dto.user.CustomerRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Setter
@Getter
public class OrderRequestDTO implements Serializable {

    @Valid
    private DeliveryAddressRequestDTO deliveryAddress;
    private BigDecimal total;
    private BigDecimal amount;
    @NotBlank(message = "El delivery mode es obligatorio")
    private String deliveryMode;
    @NotBlank(message = "El canal es obligatorio")
    private String channel;
    @NotBlank(message = "El pedido debe tener un cliente asignado")
    private CustomerRequestDTO customer;
    private String createdBy;
    @NotBlank(message = "El usuario es obligatorio")
    private String username;
    @NotEmpty(message = "Debe existir al menos un renglon en el pedido")
    @Valid
    private List<OrderEntryRequestDTO> entries;
    private boolean useCustomerAddress;  //true usa el address de customer False use deliveryAddress
    @Size(max = 255, message = "Description too long")
    private String comments;

    private String salesChannelCode;
    private String deliveryModeCode;
    private String shippingMethodCode;
    private String carrierCode;
    // --- Reglas / asignaciones
    private Boolean requiresDeliveryMan; // (opcional) el backend lo valida contra policy
    private String deliveryManMail;

    public DeliveryAddressRequestDTO getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddressRequestDTO deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(String deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public CustomerRequestDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerRequestDTO customer) {
        this.customer = customer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<OrderEntryRequestDTO> getEntries() {
        return entries;
    }

    public void setEntries(List<OrderEntryRequestDTO> entries) {
        this.entries = entries;
    }

    public boolean isUseCustomerAddress() {
        return useCustomerAddress;
    }

    public void setUseCustomerAddress(boolean useCustomerAddress) {
        this.useCustomerAddress = useCustomerAddress;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getSalesChannelCode() {
        return salesChannelCode;
    }

    public void setSalesChannelCode(String salesChannelCode) {
        this.salesChannelCode = salesChannelCode;
    }

    public String getDeliveryModeCode() {
        return deliveryModeCode;
    }

    public void setDeliveryModeCode(String deliveryModeCode) {
        this.deliveryModeCode = deliveryModeCode;
    }

    public String getShippingMethodCode() {
        return shippingMethodCode;
    }

    public void setShippingMethodCode(String shippingMethodCode) {
        this.shippingMethodCode = shippingMethodCode;
    }

    public String getCarrierCode() {
        return carrierCode;
    }

    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    public Boolean getRequiresDeliveryMan() {
        return requiresDeliveryMan;
    }

    public void setRequiresDeliveryMan(Boolean requiresDeliveryMan) {
        this.requiresDeliveryMan = requiresDeliveryMan;
    }

    public String getDeliveryManMail() {
        return deliveryManMail;
    }

    public void setDeliveryManMail(String deliveryManMail) {
        this.deliveryManMail = deliveryManMail;
    }
}
