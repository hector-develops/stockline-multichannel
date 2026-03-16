package com.devslopsleon.orders.services;

import com.devslopsleon.orders.core.dto.StoreDTO;
import com.devslopsleon.orders.core.dto.company.StoreRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStoreService {

    StoreDTO addStoreToCompany(final StoreRequestDTO store);

    StoreDTO findStoreByCompany(final String store);

    Page<StoreDTO> storesList(Pageable pageable);
}
