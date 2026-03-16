package com.devslopsleon.orders.core.repository;

import com.devslopsleon.orders.core.models.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value= "SELECT * FROM product WHERE companyid=?1 and ", nativeQuery=true)
    List<Product> findProductByCodeIncludeVariants(String companyId);

    @Query(value= "SELECT * FROM product WHERE code=?1 AND companyid=?2", nativeQuery=true)
    Optional<Product> findProductByCodeAndCompanyId(String productCode, Long company);

    @EntityGraph(attributePaths = {
            "unit",
            "brand",
            "category"
    })
    @Query("""
        SELECT p FROM Product p
        WHERE p.code = :code
          AND p.companyId = :companyId
    """)
    Optional<Product> findByCompanyIdAndCode(Long companyId, String code);

    @EntityGraph(attributePaths = {
            "unit",
            "brand",
            "category"
    })
    Page<Product> findByCompanyId(Long companyId, Pageable pageable);

    @EntityGraph(attributePaths = {
            "unit",
            "brand",
            "category"
    })
    @Query("""
        SELECT p FROM Product p
        WHERE p.companyId = :companyId
          AND (
            LOWER(p.code) LIKE LOWER(CONCAT('%', :q, '%'))
            OR LOWER(p.name) LIKE LOWER(CONCAT('%', :q, '%'))
            OR LOWER(p.description) LIKE LOWER(CONCAT('%', :q, '%'))
          )
    """)
    Page<Product> searchByCompanyId(@Param("companyId") Long companyId, @Param("q") String q, Pageable pageable);

    boolean existsByCompanyIdAndCode(Long companyId, String code);

}
