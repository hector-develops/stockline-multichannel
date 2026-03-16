package com.devslopsleon.orders.core.repository;

import com.devslopsleon.orders.core.models.types.stts.DeliveryStatusClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DeliveryStatusRepository extends JpaRepository<DeliveryStatusClass, Long> {

    @Query(value= "SELECT * FROM deliverystatus WHERE code=?1", nativeQuery=true)
    Optional<DeliveryStatusClass> findDeliveryStatusByCode(String code);
}
