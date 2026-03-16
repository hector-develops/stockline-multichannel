package com.devslopsleon.orders.core.dto.transactional.anymarket.items;

import java.io.Serializable;

public class ShippingsAnyDTO implements Serializable {

    private Long id;
    private String shippingtype;
    private String shippingCarrierNormalized;
    private String shippingCarrierTypeNormalized;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShippingtype() {
        return shippingtype;
    }

    public void setShippingtype(String shippingtype) {
        this.shippingtype = shippingtype;
    }

    public String getShippingCarrierNormalized() {
        return shippingCarrierNormalized;
    }

    public void setShippingCarrierNormalized(String shippingCarrierNormalized) {
        this.shippingCarrierNormalized = shippingCarrierNormalized;
    }

    public String getShippingCarrierTypeNormalized() {
        return shippingCarrierTypeNormalized;
    }

    public void setShippingCarrierTypeNormalized(String shippingCarrierTypeNormalized) {
        this.shippingCarrierTypeNormalized = shippingCarrierTypeNormalized;
    }
}
