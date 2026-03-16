package com.devslopsleon.orders.mappers.company;


import com.devslopsleon.orders.core.dto.company.ShippingMethodDTO;
import com.devslopsleon.orders.core.models.company.ShippingMethod;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel= "spring")
public interface ShippingMethodMapper {

    ShippingMethodDTO toDto(ShippingMethod shippingMethod);

    ShippingMethod toEntity(ShippingMethodDTO dto);

    List<ShippingMethodDTO> toDtos(List<ShippingMethod> shippingMethods);
}
