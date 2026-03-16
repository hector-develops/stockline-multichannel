package com.devslopsleon.orders.services;

import com.devslopsleon.orders.core.dto.company.SalesChannelDTO;

import java.util.List;

public interface ISalesChannelService {

    List<SalesChannelDTO> listSalesChannel();

    SalesChannelDTO findSalesChannelById(Long id);

    SalesChannelDTO updateSalesChannelById(Long id,  SalesChannelDTO salesChannelDTO);

}
