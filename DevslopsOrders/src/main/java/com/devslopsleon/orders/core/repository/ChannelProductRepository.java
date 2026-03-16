package com.devslopsleon.orders.core.repository;

import com.devslopsleon.orders.core.models.company.ChannelProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChannelProductRepository extends JpaRepository<ChannelProduct, Long> {

    boolean existsByCompanyPkAndSalesChannelCodeAndProductCodeAndEnabledTrue(
            Long companyPk, String channelCode, String productCode
    );

    @Query("""
    SELECT cp FROM ChannelProduct cp
    WHERE cp.company.pk = :companyPk
      AND cp.salesChannel.code = :channelCode
      AND cp.enabled = true
  """)
    Page<ChannelProduct> listEnabledByChannel(Long companyPk, String channelCode, Pageable pageable);
}
