package com.devslopsleon.orders.core.dto.transactional.producteca;

import java.io.Serializable;
import java.math.BigDecimal;

public class ShipmentPtecaDTO implements Serializable {
    private String TrackingNumber;
    private String TrackingUrl;
    private String Courier;
    private BigDecimal Cost;
    private String Mode;
    private String LabelUrl;

    public String getTrackingNumber() {
        return TrackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        TrackingNumber = trackingNumber;
    }

    public String getTrackingUrl() {
        return TrackingUrl;
    }

    public void setTrackingUrl(String trackingUrl) {
        TrackingUrl = trackingUrl;
    }

    public String getCourier() {
        return Courier;
    }

    public void setCourier(String courier) {
        Courier = courier;
    }

    public BigDecimal getCost() {
        return Cost;
    }

    public void setCost(BigDecimal cost) {
        Cost = cost;
    }

    public String getMode() {
        return Mode;
    }

    public void setMode(String mode) {
        Mode = mode;
    }

    public String getLabelUrl() {
        return LabelUrl;
    }

    public void setLabelUrl(String labelUrl) {
        LabelUrl = labelUrl;
    }
}
