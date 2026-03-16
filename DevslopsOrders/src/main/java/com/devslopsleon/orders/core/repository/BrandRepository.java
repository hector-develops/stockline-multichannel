package com.devslopsleon.orders.core.repository;


import com.devslopsleon.orders.core.models.product.BrandProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<BrandProduct, Long> {

    @Query(value= "SELECT * FROM brand WHERE code=?1 AND companyid=?2", nativeQuery=true)
    Optional<BrandProduct> findBrandByCodeAndCompany(String code, Long companyId);

    @Query(value= "SELECT * FROM brand WHERE pk=?1 AND companyid=?2", nativeQuery=true)
    Optional<BrandProduct> findBrandByIdAndCompany(Long id, Long companyId);

    @Query(value= "SELECT * FROM brand WHERE companyid=?1", nativeQuery=true)
    List<BrandProduct> findBrandListByCompany(Long companyId);

    @Query(value= "SELECT * FROM brand WHERE companyid=?1", nativeQuery=true)
    Page<BrandProduct> findBrandListByCompanyPage(Long companyId, Pageable pageable);
}
