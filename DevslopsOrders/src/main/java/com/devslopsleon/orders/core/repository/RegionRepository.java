package com.devslopsleon.orders.core.repository;

import com.devslopsleon.orders.core.models.address.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {

    @Query(value= "SELECT * FROM region WHERE code=?1", nativeQuery=true)
    Optional<Region> findRegionByCodeAndStatus(String code);
}
