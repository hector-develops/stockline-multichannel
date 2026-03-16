package com.devslopsleon.orders.core.repository;

import com.devslopsleon.orders.core.models.product.ProductVariantLow;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductVariantLowRepository extends JpaRepository<ProductVariantLow, Long> {

    boolean existsByCompanyIdAndCode(final Long companyId, final String code);


    @EntityGraph(attributePaths = {
            "productVariant",
            "productVariant.product",
            "productVariant.product.unit",
            "productVariant.product.brand",
            "productVariant.product.category"
    })
    @Query("""
        SELECT pvl FROM ProductVariantLow pvl
        WHERE pvl.companyId = :companyId
          AND pvl.code = :code
    """)
    Optional<ProductVariantLow> findByCompanyIdAndCodeWithProduct(
            @Param("companyId") Long companyId,
            @Param("code") String code
    );
}
