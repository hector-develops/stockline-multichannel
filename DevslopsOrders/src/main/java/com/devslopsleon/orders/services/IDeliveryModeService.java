package com.devslopsleon.orders.services;

import com.devslopsleon.orders.core.dto.company.DeliveryModeDTO;
import com.devslopsleon.orders.core.dto.company.DeliveryModeRequestDTO;

import java.util.List;

public interface IDeliveryModeService {

    DeliveryModeDTO addDeliveryMode(final DeliveryModeRequestDTO dm);

    List<DeliveryModeDTO> deliveries ();

    DeliveryModeDTO findDeliveryModeByCode(final String delivery);

    String updateDeliveryMode (final DeliveryModeDTO deliveryModeDTO);

    DeliveryModeDTO findDeliveryModeById(final Long id);
}
