package com.devslopsleon.orders.core.repository;

import com.devslopsleon.orders.core.models.product.UnitOfMeasure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UnitOfMeasureRepository extends JpaRepository<UnitOfMeasure, Long> {

    @Query(value= "SELECT * FROM unitsofmeasure WHERE code=?1 and companyid=?2", nativeQuery=true)
    Optional<UnitOfMeasure> findUnitByCodeAndCompany(final String code, Long companyId);

    @Query(value= "SELECT * FROM unitsofmeasure WHERE pk=?1 and companyid=?2", nativeQuery=true)
    Optional<UnitOfMeasure> findUnitByIdAndCompany(final Long id, Long companyId);

    @Query(value= "SELECT * FROM unitsofmeasure WHERE companyid=?1", nativeQuery=true)
    List<UnitOfMeasure> findUnitsByCompany(final Long companyId);

}
