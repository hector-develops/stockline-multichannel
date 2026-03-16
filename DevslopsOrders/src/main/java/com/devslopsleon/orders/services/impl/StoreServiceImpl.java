package com.devslopsleon.orders.services.impl;


import com.devslopsleon.orders.core.dto.StoreDTO;
import com.devslopsleon.orders.core.dto.company.StoreRequestDTO;
import com.devslopsleon.orders.core.models.address.Region;
import com.devslopsleon.orders.core.models.company.Company;
import com.devslopsleon.orders.core.models.company.Store;
import com.devslopsleon.orders.core.repository.CompanyRepository;
import com.devslopsleon.orders.core.repository.RegionRepository;
import com.devslopsleon.orders.core.repository.StoreRepository;
import com.devslopsleon.orders.mappers.StoreMapper;
import com.devslopsleon.orders.services.IStoreService;
import com.devslopsleon.orders.services.auth.TenantContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl implements IStoreService {

    private final StoreRepository storeRepository;
    private final CompanyRepository companyRepository;
    private final RegionRepository regionRepository;
    private final StoreMapper storeMapper;

    @Override
    @Transactional
    public StoreDTO addStoreToCompany(final StoreRequestDTO store) {

        //TODO Validate if exist company By CompanyId
        Long companyId = TenantContext.getCompanyPk();
        if (companyId == null) {
            throw new RuntimeException("Tenant not resolved"); // o AccessDenied
        }
        Company company = companyRepository.findCompanyById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        // validate if region exist
        Region region = regionRepository.findRegionByCodeAndStatus(store.getAddress().getRegion()).orElseThrow(
                () -> new RuntimeException("Region not found")
        );
        //Validate if store exist uid,companyId
        Optional<Store> storeExist = storeRepository.existStoreByCompany(store.getUid(), companyId);
        if(storeExist.isPresent()) {
            throw new RuntimeException("Store already exists");
        }
        Store s = storeMapper.toEntity(store);
        s.setCompany(company);
        s.getWarehouse().setCompany(company);
        s.getWarehouse().setWarehouseStore(true);
        s.getWarehouse().setWarehouseCompany(false);
        s.getAddress().setCompany(company);
        //TODO set to store and save
        Store storeSaved = storeRepository.save(s);
        //TODO map to storeDTO and return
        return null;
    }

    @Override
    public StoreDTO findStoreByCompany(String store) {

        //TODO find company by companyId

        //TODO if exist, find under list stores the store request
        return null;
    }

    @Override
    public Page<StoreDTO> storesList(Pageable pageable) {
        Long companyId = TenantContext.getCompanyPk();
        if (companyId == null) {
            throw new RuntimeException("Tenant not resolved"); // o AccessDenied
        }
        Page<Store> page;
        page = storeRepository.findStoresPageByCompanyId(companyId, pageable);
        return page.map(storeMapper::toDTO);
    }

    public StoreServiceImpl(StoreRepository storeRepository, CompanyRepository companyRepository, RegionRepository regionRepository, StoreMapper storeMapper) {
        this.storeRepository = storeRepository;
        this.companyRepository = companyRepository;
        this.regionRepository = regionRepository;
        this.storeMapper = storeMapper;
    }
}
