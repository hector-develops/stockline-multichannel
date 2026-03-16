package com.devslopsleon.orders.core.dto.transactional.anymarket;

import java.io.Serializable;

public class PaymentAnyDTO implements Serializable {

    private String method;
    private String status;
    private Double value;
    private String marketplaceId;
    private String paymentMethodNormalized;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getMarketplaceId() {
        return marketplaceId;
    }

    public void setMarketplaceId(String marketplaceId) {
        this.marketplaceId = marketplaceId;
    }

    public String getPaymentMethodNormalized() {
        return paymentMethodNormalized;
    }

    public void setPaymentMethodNormalized(String paymentMethodNormalized) {
        this.paymentMethodNormalized = paymentMethodNormalized;
    }
}
