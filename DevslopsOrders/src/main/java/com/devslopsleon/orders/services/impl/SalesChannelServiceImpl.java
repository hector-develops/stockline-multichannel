package com.devslopsleon.orders.services.impl;


import com.devslopsleon.orders.core.dto.company.SalesChannelDTO;
import com.devslopsleon.orders.core.models.company.SalesChannel;
import com.devslopsleon.orders.core.repository.SalesChannelRepository;
import com.devslopsleon.orders.mappers.company.SalesChannelMapper;
import com.devslopsleon.orders.services.ISalesChannelService;
import com.devslopsleon.orders.services.auth.TenantContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesChannelServiceImpl implements ISalesChannelService {

    private final SalesChannelRepository salesChannelRepository;
    private final SalesChannelMapper salesChannelMapper;

    @Override
    public List<SalesChannelDTO> listSalesChannel() {
        Long companyId = TenantContext.getCompanyPk();
        if (companyId == null) {
            throw new RuntimeException("Tenant not resolved");
        }
        List<SalesChannel> channels = salesChannelRepository.findSalesChannelByCompany(companyId);
        return salesChannelMapper.toDTO(channels);
    }

    @Override
    public SalesChannelDTO findSalesChannelById(Long id) {
        return null;
    }

    @Override
    public SalesChannelDTO updateSalesChannelById(Long id, SalesChannelDTO salesChannelDTO) {
        return null;
    }

    public SalesChannelServiceImpl(SalesChannelRepository salesChannelRepository, SalesChannelMapper salesChannelMapper) {
        this.salesChannelRepository = salesChannelRepository;
        this.salesChannelMapper = salesChannelMapper;
    }
}
