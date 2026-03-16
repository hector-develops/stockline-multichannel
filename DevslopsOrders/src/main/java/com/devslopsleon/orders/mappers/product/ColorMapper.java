package com.devslopsleon.orders.mappers.product;


import com.devslopsleon.orders.core.dto.product.ColorDTO;
import com.devslopsleon.orders.core.dto.product.ColorRequestDTO;
import com.devslopsleon.orders.core.models.product.ColorProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ColorMapper {

    //ColorMapper mapper = Mappers.getMapper(ColorMapper.class);

    @Mapping(source = "pk", target = "id")
    @Mapping(source = "wStatus", target = "active")
    ColorDTO toColorDTO(ColorProduct colorProduct);

    @Mapping(source = "active", target = "wStatus")
    ColorProduct toEntity(ColorRequestDTO colorRequestDTO);

    @Mapping(source = "active", target = "wStatus")
    ColorProduct toEntityFromDTO(ColorDTO colorDTO);

    List<ColorDTO> toListDTO(List<ColorProduct> colors);

}
