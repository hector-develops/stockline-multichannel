package com.devslopsleon.orders.mappers.address;


import com.devslopsleon.orders.core.dto.address.DeliveryAddressDTO;
import com.devslopsleon.orders.core.models.address.DeliveryAddress;
import org.mapstruct.Mapper;

@Mapper(componentModel= "spring", uses = { RegionMapper.class })
public interface DeliveryAddressMapper {

    DeliveryAddressDTO toDTO(DeliveryAddress dA);


}
