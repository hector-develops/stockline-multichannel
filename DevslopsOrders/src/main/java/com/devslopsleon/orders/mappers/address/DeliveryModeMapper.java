package com.devslopsleon.orders.mappers.address;


import com.devslopsleon.orders.core.dto.company.DeliveryModeDTO;
import com.devslopsleon.orders.core.dto.company.DeliveryModeRequestDTO;
import com.devslopsleon.orders.core.models.types.mov.DeliveryMode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel= "spring")
public interface DeliveryModeMapper {

    //DeliveryModeMapper mapper = Mappers.getMapper(DeliveryModeMapper.class);

    @Mapping(source = "pk", target = "id")
    @Mapping(source = "wStatus", target = "active")
    DeliveryModeDTO toDTO(DeliveryMode dm);

    @Mapping(source = "active", target = "wStatus")
    DeliveryMode toEntity(DeliveryModeRequestDTO deliveryModeRequestDTO);

}
