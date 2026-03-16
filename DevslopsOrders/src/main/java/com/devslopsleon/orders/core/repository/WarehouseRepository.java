package com.devslopsleon.orders.core.repository;

import com.devslopsleon.orders.core.models.company.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    @Query(value= "SELECT * FROM warehouse WHERE code=?1 and company=?2 and wstatus=1", nativeQuery=true)
    Optional<Warehouse> findWarehouseByCompanyAndCodeAndStatus(final String code, final Long company);

    @Query(value= "SELECT * FROM warehouse WHERE code=?1 and company=?2 and wstatus=1", nativeQuery=true)
    Optional<Warehouse> findActiveByCompanyAndCode(String code, Long companyPk);

    @Query(value= "SELECT * FROM warehouse WHERE company=?1 and wstatus=1", nativeQuery=true)
    List<Warehouse> listWarehousesByCompany(Long company);
}
