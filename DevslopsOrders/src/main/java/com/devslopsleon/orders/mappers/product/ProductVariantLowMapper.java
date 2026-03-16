package com.devslopsleon.orders.mappers.product;


import com.devslopsleon.orders.core.dto.product.ProductVariantLowDTO;
import com.devslopsleon.orders.core.dto.product.request.ProductVariantLowRequestDTO;
import com.devslopsleon.orders.core.models.product.ProductVariantLow;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductVariantLowMapper {

    //ProductVariantLowMapper mapper = Mappers.getMapper(ProductVariantLowMapper.class);


    ProductVariantLow toEntity(ProductVariantLowRequestDTO productVariantLowRequestDTO);

    @Mapping(source = "pk", target = "id")
    ProductVariantLowDTO toDTO(ProductVariantLow productVariantLow);

}
