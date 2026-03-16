package com.devslopsleon.orders.services;

import com.devslopsleon.orders.core.dto.company.ShippingMethodDTO;
import com.devslopsleon.orders.core.models.company.DeliveryCarrier;

import java.util.List;

public interface IShippingMethodService {

    List<ShippingMethodDTO> listShippingMethods();

    ShippingMethodDTO findShippingMethodById(Long id);

}
