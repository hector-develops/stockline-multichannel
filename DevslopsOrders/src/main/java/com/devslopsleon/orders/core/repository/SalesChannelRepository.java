package com.devslopsleon.orders.core.repository;

import com.devslopsleon.orders.core.models.company.SalesChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SalesChannelRepository extends JpaRepository<SalesChannel, Long> {

    @Query(value= "SELECT * FROM saleschannel WHERE code=?1 and companyid=?2 and wstatus=1", nativeQuery=true)
    Optional<SalesChannel> findSalesChannelByCompanyAndCode(final String code, final Long company);

    @Query(value= "SELECT * FROM saleschannel WHERE companyid=?1 and wstatus=1", nativeQuery=true)
    List<SalesChannel> findSalesChannelByCompany(final Long company);

}
