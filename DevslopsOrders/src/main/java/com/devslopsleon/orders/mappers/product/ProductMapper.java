package com.devslopsleon.orders.mappers.product;


import com.devslopsleon.orders.core.dto.product.ProductDTO;
import com.devslopsleon.orders.core.dto.product.request.ProductRequestDTO;
import com.devslopsleon.orders.core.models.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel= "spring", uses = {ProductVariantMapper.class, BrandMapper.class,
        CategoryMapper.class, ProductVariantLowMapper.class})
public interface ProductMapper {

    @Mapping(source = "active", target = "wStatus")
    @Mapping(target = "brand", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "variants", ignore=true)
    Product toEntity(ProductRequestDTO productRequestDTO);


    @Mapping(target = "id", source = "pk")
    //@Mapping(target = "price", expression = "java(java.math.BigDecimal.valueOf(product.getPrice()))")
    @Mapping(target = "unit", expression = "java(product.getUnit() != null ? product.getUnit().getCode() : null)")
    @Mapping(target = "brand", expression = "java(product.getBrand() != null ? product.getBrand().getCode() : null)")
    @Mapping(target = "category", expression = "java(product.getCategory() != null ? product.getCategory().getCode() : null)")
    @Mapping(target = "active", source = "wStatus")
    //@Mapping(target = "brand", ignore = true)
    //@Mapping(target = "category", ignore = true)
    //@Mapping(target = "variants", ignore=true)
    ProductDTO toDTO(Product product);

    /*
    @Mapping(target = "id", source = "pk")
    @Mapping(target = "color", expression = "java(variant.getColorProduct() != null ? variant.getColorProduct().getCode() : null)")
    @Mapping(target = "active", source = "wStatus")
    ProductVariantDTO PRODUCT_VARIANT_DTO(ProductVariant variant);

    @Mapping(target = "id", source = "pk")
    @Mapping(target = "active", source = "wStatus")
    ProductVariantLowDTO VARIANT_LOW_DTO(ProductVariantLow low);

     */

}
