package com.devslopsleon.orders.mappers.product;


import com.devslopsleon.orders.core.dto.product.BrandDTO;
import com.devslopsleon.orders.core.dto.product.BrandRequestDTO;
import com.devslopsleon.orders.core.models.product.BrandProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    //BrandMapper mapper = Mappers.getMapper(BrandMapper.class);

    @Mapping(source = "pk", target = "id")
    @Mapping(source = "wStatus", target = "active")
    BrandDTO toBrandDTO(BrandProduct brandProduct);

    @Mapping(source = "active", target = "wStatus")
    BrandProduct toEntity(BrandDTO brandDTO);

    @Mapping(source = "active", target = "wStatus")
    BrandProduct toEntityFromRequest(BrandRequestDTO brandRequestDTO);

    List<BrandDTO> toListDTOs(List<BrandProduct> entities);

    List<BrandProduct> toListEntities(List<BrandDTO> dtos);

}
