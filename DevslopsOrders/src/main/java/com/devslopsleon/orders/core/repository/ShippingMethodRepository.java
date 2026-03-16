package com.devslopsleon.orders.core.repository;

import com.devslopsleon.orders.core.models.company.ShippingMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShippingMethodRepository extends JpaRepository<ShippingMethod, Long> {

    @Query(value= "SELECT * FROM shippingmethod WHERE companyid=?1 and wstatus=1", nativeQuery=true)
    List<ShippingMethod> listShippingMethodsByCompany(Long companyId);
}
