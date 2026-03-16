package com.devslopsleon.orders.mappers.address;


import com.devslopsleon.orders.core.dto.company.CourierDTO;
import com.devslopsleon.orders.core.models.company.DeliveryCarrier;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel= "spring")
public interface DeliveryCarrierMapper {

    CourierDTO toDto(DeliveryCarrier deliveryCarrier);

    DeliveryCarrier toEntity(CourierDTO dto);

    List<DeliveryCarrier> toDto(List<DeliveryCarrier> deliveryCarriers);
}
