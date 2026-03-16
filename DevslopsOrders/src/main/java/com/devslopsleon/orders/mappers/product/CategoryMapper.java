package com.devslopsleon.orders.mappers.product;

import com.devslopsleon.orders.core.dto.product.CategoryDTO;
import com.devslopsleon.orders.core.dto.product.CategoryRequestDTO;
import com.devslopsleon.orders.core.models.product.CategoryProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    //CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);

    @Mapping(source = "pk", target = "id")
    @Mapping(source = "wStatus", target = "active")
    CategoryDTO toCategoryDTO(CategoryProduct categoryProduct);

    @Mapping(source = "active", target = "wStatus")
    CategoryProduct toCategoryEntity(CategoryRequestDTO categoryDTO);

    List<CategoryDTO> mapCategories(List<CategoryProduct> entities);

    List<CategoryProduct> toEntitiesList(List<CategoryDTO> dtos);

}
