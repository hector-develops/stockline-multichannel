package com.devslopsleon.orders.core.dto.transactional.producteca;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

public class InvoiceIntegrationDTO implements Serializable {

    private BigInteger IntegrationId;
    private int App;
    private LocalDateTime CreatedAt;
    private boolean DecreaseStock;

    public BigInteger getIntegrationId() {
        return IntegrationId;
    }

    public void setIntegrationId(BigInteger integrationId) {
        IntegrationId = integrationId;
    }

    public int getApp() {
        return App;
    }

    public void setApp(int app) {
        App = app;
    }

    public LocalDateTime getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        CreatedAt = createdAt;
    }

    public boolean isDecreaseStock() {
        return DecreaseStock;
    }

    public void setDecreaseStock(boolean decreaseStock) {
        DecreaseStock = decreaseStock;
    }
}
