package com.devslopsleon.orders.mappers.product;


import com.devslopsleon.orders.core.dto.product.UnitOfMeasureDTO;
import com.devslopsleon.orders.core.models.product.UnitOfMeasure;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UnitMapper {

    //UnitMapper mapper = Mappers.getMapper(UnitMapper.class);
    @Mapping(source = "active", target = "wStatus")
    UnitOfMeasure toEntity(UnitOfMeasureDTO unit);

    @Mapping(source = "pk", target = "id")
    @Mapping(source = "wStatus", target = "active")
    UnitOfMeasureDTO toUnitDTO(UnitOfMeasure unit);

    List<UnitOfMeasureDTO> units(List<UnitOfMeasure> unitsM);

    List<UnitOfMeasure> unitsModel(List<UnitOfMeasureDTO> units);


}
