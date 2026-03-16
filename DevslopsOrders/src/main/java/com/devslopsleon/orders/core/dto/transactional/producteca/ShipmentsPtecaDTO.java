package com.devslopsleon.orders.core.dto.transactional.producteca;

import java.io.Serializable;

public class ShipmentsPtecaDTO implements Serializable {

    ShipmentPtecaDTO Shipment;

    public ShipmentPtecaDTO getShipment() {
        return Shipment;
    }

    public void setShipment(ShipmentPtecaDTO shipment) {
        Shipment = shipment;
    }
}
