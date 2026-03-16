package com.devslopsleon.orders.core.repository;

import com.devslopsleon.orders.core.dto.product.ProductWarehousePageDTO;
import com.devslopsleon.orders.core.models.company.StockLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StockLevelRepository extends JpaRepository<StockLevel, Long> {

    @Query(value= "SELECT * FROM stocklevel WHERE productCode=?1 AND warehouse=?2", nativeQuery=true)
    Optional<StockLevel> findStockLevelByProductCodeAndWarehouse(String productCode, String warehouse);

    @Query(value= "SELECT * FROM stocklevel WHERE productCode=?1 AND warehouseid=?2 AND company_id=?3", nativeQuery=true)
    Optional<StockLevel> findStockByproductAndWarehouseId(String product, Long warehouse, Long companyId);


    @Query("""
    SELECT new com.devslopsleon.orders.core.dto.product.ProductWarehousePageDTO(
        pvl.code,
        p.name,
        pv.name,
        pvl.description,
        pvl.size,
        pvl.price,
        w.code,
        w.pk,
        s.available,
        s.reserved,
        s.overselling,
        u.code,
        b.name,
        c.name,
        pvl.wStatus
    )
    FROM StockLevel s
    JOIN Warehouse w ON w.pk = s.warehouseId
    JOIN ProductVariantLow pvl ON pvl.code = s.productCode AND pvl.companyId = s.companyId
    JOIN pvl.productVariant pv
    JOIN pv.product p
    LEFT JOIN p.unit u
    LEFT JOIN p.brand b
    LEFT JOIN p.category c
    WHERE s.companyId = :companyId
      AND w.pk = :warehouseId
      AND (
         :q IS NULL OR
         LOWER(pvl.code) LIKE LOWER(CONCAT('%', :q, '%')) OR
         LOWER(p.name) LIKE LOWER(CONCAT('%', :q, '%')) OR
         LOWER(pv.name) LIKE LOWER(CONCAT('%', :q, '%')) OR
         LOWER(pvl.description) LIKE LOWER(CONCAT('%', :q, '%'))
      )
""")
    Page<ProductWarehousePageDTO> findProductsByWarehouseAndSearch(
            @Param("companyId") Long companyId,
            @Param("warehouseId") Long warehouseId,
            @Param("q") String q,
            Pageable pageable
    );
}
