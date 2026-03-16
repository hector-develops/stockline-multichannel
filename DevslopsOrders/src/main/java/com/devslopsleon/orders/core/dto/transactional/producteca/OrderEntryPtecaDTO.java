package com.devslopsleon.orders.core.dto.transactional.producteca;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderEntryPtecaDTO implements Serializable {

    private BigDecimal Price;
    private int Quantity;
    private String Description;
    private String Code;
    private String Sku;
    private String Mode;

    public BigDecimal getPrice() {
        return Price;
    }

    public void setPrice(BigDecimal price) {
        Price = price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getSku() {
        return Sku;
    }

    public void setSku(String sku) {
        Sku = sku;
    }

    public String getMode() {
        return Mode;
    }

    public void setMode(String mode) {
        Mode = mode;
    }
}
