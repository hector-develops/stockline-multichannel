package com.devslopsleon.orders.services.impl;


import com.devslopsleon.orders.core.dto.WarehouseDTO;
import com.devslopsleon.orders.core.models.company.Warehouse;
import com.devslopsleon.orders.core.repository.WarehouseRepository;
import com.devslopsleon.orders.mappers.WarehouseMapper;
import com.devslopsleon.orders.services.IWarehouseService;
import com.devslopsleon.orders.services.auth.TenantContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseServiceImpl implements IWarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final WarehouseMapper warehouseMapper;

    @Override
    public Warehouse findWarehouseByCompanyAndCode(String company, String warehouse) {
        return null;
    }

    @Override
    public WarehouseDTO findWarehouseDTOByWarehouseCode(String warehouseCode) {
        return null;
    }

    @Override
    public List<WarehouseDTO> listWarehouses() {
        Long companyPk = TenantContext.getCompanyPk();
        if (companyPk == null) {
            throw new RuntimeException("Tenant not resolved"); // o AccessDenied
        }
        List<Warehouse> warehouses = warehouseRepository.listWarehousesByCompany(companyPk);
        return warehouseMapper.toDTO(warehouses);
    }

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository, WarehouseMapper warehouseMapper) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseMapper = warehouseMapper;
    }
}
