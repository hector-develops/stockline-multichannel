package com.devslopsleon.orders.services.impl;

import com.devslopsleon.orders.core.dto.product.CategoryDTO;
import com.devslopsleon.orders.core.dto.product.CategoryRequestDTO;
import com.devslopsleon.orders.core.models.product.CategoryProduct;
import com.devslopsleon.orders.core.repository.CategoryRepository;
import com.devslopsleon.orders.mappers.product.CategoryMapper;
import com.devslopsleon.orders.services.ICateogryService;
import com.devslopsleon.orders.services.auth.TenantContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICateogryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDTO createCategory(CategoryRequestDTO categoryDTO) {
        Long companyPk = TenantContext.getCompanyPk();
        if (companyPk == null) {
            throw new RuntimeException("Tenant not resolved"); // o AccessDenied
        }
        CategoryProduct cp = categoryMapper.toCategoryEntity(categoryDTO);
        cp.setCompanyId(companyPk);
        //TODO validate if category exist in company
        Optional<CategoryProduct> cv = categoryRepository.findCategoryByCode(categoryDTO.getCode(), companyPk);
        if(cv.isPresent()){
            throw new RuntimeException("Category exist!");
        }
        CategoryProduct categorySaved = categoryRepository.save(cp);
        return categoryMapper.toCategoryDTO(categorySaved);
    }

    @Override
    public void createCategoryCSV(CategoryRequestDTO categoryDTO) {

    }

    @Override
    public List<CategoryDTO> findCategoriesByCompany() {

        Long companyPk = TenantContext.getCompanyPk();
        if (companyPk == null) {
            throw new RuntimeException("Tenant not resolved"); // o AccessDenied
        }
        //Find categories by Company and status active
        List<CategoryProduct> categories = categoryRepository.getAllCategoriesByCompany(companyPk);

        return categoryMapper.mapCategories(categories);
    }

    @Override
    public Page<CategoryDTO> listCategoryPage(String q, Pageable pageable) {
        Long companyPk = TenantContext.getCompanyPk();
        if (companyPk == null) {
            throw new RuntimeException("Tenant not resolved"); // o AccessDenied
        }
        Page<CategoryProduct> page;
        if (q != null && !q.trim().isEmpty()){
            page = categoryRepository.getAllCategoriesByCompanyPage(companyPk, pageable);
        }else{
            page = categoryRepository.getAllCategoriesByCompanyPage(companyPk, pageable);
        }
        return page.map(categoryMapper::toCategoryDTO);
    }

    @Override
    public CategoryDTO findCategoryByCode(final String categoryCode) {
        return null;
    }

    @Override
    public CategoryDTO findCategoryById(final Long id) {
        Long companyPk = TenantContext.getCompanyPk();
        if (companyPk == null) {
            throw new RuntimeException("Tenant not resolved"); // o AccessDenied
        }
        Optional<CategoryProduct> cp = categoryRepository.findCategoryById(id, companyPk);
        if(cp.isEmpty()){
            throw new RuntimeException("Category not found");
        }
        return categoryMapper.toCategoryDTO(cp.get());
    }

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }
}
