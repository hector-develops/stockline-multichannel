package com.devslopsleon.orders.core.repository;

import com.devslopsleon.orders.core.models.product.ColorProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ColorRepository extends JpaRepository<ColorProduct, Long> {

    @Query(value= "SELECT * FROM color WHERE code=?1 AND companyid=?2", nativeQuery=true)
    Optional<ColorProduct> findColorByCodeCompany(String code, Long companyId);

    @Query(value= "SELECT * FROM color WHERE pk=?1 AND companyid=?2", nativeQuery=true)
    Optional<ColorProduct> findColorByIdCompanyAndCode(final Long id, Long companyId);

    @Query(value= "SELECT * FROM color WHERE companyid=?1", nativeQuery=true)
    List<ColorProduct> findColorsByCompany(final Long companyId);
}
