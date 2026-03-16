package com.devslopsleon.orders.services;

import com.devslopsleon.orders.core.dto.product.CategoryDTO;
import com.devslopsleon.orders.core.dto.product.CategoryRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICateogryService {

    CategoryDTO createCategory(final CategoryRequestDTO categoryDTO);

    void createCategoryCSV(final CategoryRequestDTO categoryDTO);

    List<CategoryDTO> findCategoriesByCompany();

    Page<CategoryDTO> listCategoryPage(String q, Pageable pageable);

    CategoryDTO findCategoryByCode(final String categoryCode);

    CategoryDTO findCategoryById(final Long id);
}
