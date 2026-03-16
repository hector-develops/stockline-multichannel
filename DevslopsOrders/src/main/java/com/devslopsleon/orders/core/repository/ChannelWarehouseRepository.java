package com.devslopsleon.orders.core.repository;

import com.devslopsleon.orders.core.models.company.ChannelWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChannelWarehouseRepository extends JpaRepository<ChannelWarehouse, Long> {

    @Query("""
    SELECT cw FROM ChannelWarehouse cw
    WHERE cw.company.pk = :companyPk
      AND cw.salesChannel.code = :channelCode
      AND cw.enabled = true
    ORDER BY cw.priority ASC
  """)
    List<ChannelWarehouse> findEnabledWarehousesByChannel(Long companyPk, String channelCode);

    boolean existsByCompanyPkAndSalesChannelCodeAndWarehousePk(Long companyPk, String channelCode, Long warehousePk);
}
