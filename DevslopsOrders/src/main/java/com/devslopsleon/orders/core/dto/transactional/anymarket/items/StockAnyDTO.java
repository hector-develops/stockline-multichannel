package com.devslopsleon.orders.core.dto.transactional.anymarket.items;

import java.io.Serializable;

public class StockAnyDTO implements Serializable {
    private Long stockLocalId;
    private Double amount;
    private String stockName;

    public Long getStockLocalId() {
        return stockLocalId;
    }

    public void setStockLocalId(Long stockLocalId) {
        this.stockLocalId = stockLocalId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }
}
