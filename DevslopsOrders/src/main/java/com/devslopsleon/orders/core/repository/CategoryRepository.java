package com.devslopsleon.orders.core.repository;

import com.devslopsleon.orders.core.models.product.CategoryProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryProduct, Long> {

    @Query(value= "SELECT * FROM category WHERE code=?1 AND companyid=?2", nativeQuery=true)
    Optional<CategoryProduct> findCategoryByCode(String code, Long companyId);

    @Query(value= "SELECT * FROM category WHERE pk=?1 AND companyid=?2", nativeQuery=true)
    Optional<CategoryProduct> findCategoryById(Long id, Long companyid);

    @Query(value= "SELECT * FROM category WHERE companyid=?1", nativeQuery=true)
    List<CategoryProduct> getAllCategoriesByCompany(Long companyId);

    @Query(value= "SELECT * FROM category WHERE companyid=?1", nativeQuery=true)
    Page<CategoryProduct> getAllCategoriesByCompanyPage(Long companyId, Pageable pageable);

}
