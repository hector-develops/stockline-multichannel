package com.devslopsleon.orders.core.repository;

import com.devslopsleon.orders.core.models.types.mov.DeliveryMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DeliveryModeRepository extends JpaRepository<DeliveryMode, Long> {

    @Query(value= "SELECT * FROM deliverymode WHERE code=?1 AND companyid=?2", nativeQuery=true)
    Optional<DeliveryMode> findDeliveryModeByCode(final String code, final Long companyId);

    @Query(value= "SELECT * FROM deliverymode WHERE pk=?1 AND companyid=?2", nativeQuery=true)
    Optional<DeliveryMode> findDeliveryModeById(final Long id, final Long companyId);

    @Query(value= "SELECT * FROM deliverymode WHERE companyid=?1", nativeQuery=true)
    List<DeliveryMode> findDeliveriesByCompanyId(Long companyId);
}
