package com.devslopsleon.orders.mappers.product;


import com.devslopsleon.orders.core.dto.product.ProductVariantDTO;
import com.devslopsleon.orders.core.dto.product.request.ProductVariantRequestDTO;
import com.devslopsleon.orders.core.models.product.ProductVariant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {ProductVariantLowMapper.class, ColorMapper.class})
public interface ProductVariantMapper {

    //ProductVariantMapper mapper = Mappers.getMapper(ProductVariantMapper.class);

    @Mapping(target = "variantsLow", ignore = true)
    //@Mapping(target = "color", ignore = true)
    ProductVariant toEntity(ProductVariantRequestDTO productVariantDTO);

    @Mapping(source = "pk", target = "id")
    @Mapping(source = "wStatus", target = "active")
    ProductVariantDTO toDTO(ProductVariant productVariant);

}
