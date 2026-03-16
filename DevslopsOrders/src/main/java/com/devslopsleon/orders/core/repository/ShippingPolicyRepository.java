package com.devslopsleon.orders.core.repository;

import com.devslopsleon.orders.core.models.company.ShippingPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ShippingPolicyRepository extends JpaRepository<ShippingPolicy, Long> {

    @Query("""
        SELECT p FROM ShippingPolicy p
        WHERE p.company.pk = :companyPk
          AND p.enabled = true
          AND p.salesChannel.code = :salesChannelCode
          AND p.deliveryMode.code = :deliveryModeCode
          AND p.shippingMethod.code = :shippingMethodCode
          AND p.carrier.code = :carrierCode
    """)
    Optional<ShippingPolicy> findValidPolicy(
            Long companyPk,
            String salesChannelCode,
            String deliveryModeCode,
            String shippingMethodCode,
            String carrierCode
    );
}
