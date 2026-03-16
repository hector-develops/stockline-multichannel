package com.devslopsleon.orders.core.dto.transactional.anymarket;

import com.devslopsleon.orders.core.dto.transactional.anymarket.items.ShippingsAnyDTO;
import com.devslopsleon.orders.core.dto.transactional.anymarket.items.StockAnyDTO;

import java.io.Serializable;
import java.util.List;

public class OrderItemDTO implements Serializable {

    private Double amount;
    private Double unit;
    private Double gross;
    private Double total;
    private Double discount;
    private Long idInMarketPlace;
    private List<ShippingsAnyDTO> shippings;
    private Long marketPlaceId;
    private Long orderItemId;
    private boolean freeShipping;
    private List<StockAnyDTO> stocks;
    private boolean isCatalog;
    private String idInMarketplaceCatalogOrigin;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getUnit() {
        return unit;
    }

    public void setUnit(Double unit) {
        this.unit = unit;
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

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Long getIdInMarketPlace() {
        return idInMarketPlace;
    }

    public void setIdInMarketPlace(Long idInMarketPlace) {
        this.idInMarketPlace = idInMarketPlace;
    }

    public List<ShippingsAnyDTO> getShippings() {
        return shippings;
    }

    public void setShippings(List<ShippingsAnyDTO> shippings) {
        this.shippings = shippings;
    }

    public Long getMarketPlaceId() {
        return marketPlaceId;
    }

    public void setMarketPlaceId(Long marketPlaceId) {
        this.marketPlaceId = marketPlaceId;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public boolean isFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(boolean freeShipping) {
        this.freeShipping = freeShipping;
    }

    public List<StockAnyDTO> getStocks() {
        return stocks;
    }

    public void setStocks(List<StockAnyDTO> stocks) {
        this.stocks = stocks;
    }

    public boolean isCatalog() {
        return isCatalog;
    }

    public void setCatalog(boolean catalog) {
        isCatalog = catalog;
    }

    public String getIdInMarketplaceCatalogOrigin() {
        return idInMarketplaceCatalogOrigin;
    }

    public void setIdInMarketplaceCatalogOrigin(String idInMarketplaceCatalogOrigin) {
        this.idInMarketplaceCatalogOrigin = idInMarketplaceCatalogOrigin;
    }
}
