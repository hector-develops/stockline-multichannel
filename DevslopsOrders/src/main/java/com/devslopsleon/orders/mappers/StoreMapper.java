package com.devslopsleon.orders.mappers;


import com.devslopsleon.orders.core.dto.StoreDTO;
import com.devslopsleon.orders.core.dto.company.StoreRequestDTO;
import com.devslopsleon.orders.core.models.company.Store;
import com.devslopsleon.orders.mappers.address.AddressMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = { WarehouseMapper.class, AddressMapper.class })
public interface StoreMapper {

    @Mapping(source = "pk", target = "id")
    @Mapping(source = "wStatus", target = "active")
    //@Mapping(source = "company.uid", target = "company")
    StoreDTO toDTO(Store entity);

    @Mapping(source = "active", target = "wStatus")
    @Mapping(target = "address.region", ignore = true)
    Store toEntity(StoreRequestDTO dto);

    List<StoreDTO> toDTOs(List<Store> entities);
}
