package com.devslopsleon.orders.mappers;


import com.devslopsleon.orders.core.dto.WarehouseDTO;
import com.devslopsleon.orders.core.dto.company.WarehouseRequestDTO;
import com.devslopsleon.orders.core.models.company.Warehouse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {


    @Mapping(source = "pk", target = "id")
    @Mapping(source = "wStatus", target = "active")
    @Mapping(source = "defaultWH", target = "default")
    @Mapping(source = "creationTime", target = "date")
    @Mapping(source = "modifiedTime", target = "modified")
    WarehouseDTO toDTO(Warehouse entity);


    List<WarehouseDTO> toDTO(List<Warehouse> entity);

    @Mapping(target = "pk", ignore = true)
    Warehouse toEntityFromRequest(WarehouseRequestDTO warehouseRequestDTO);
}
