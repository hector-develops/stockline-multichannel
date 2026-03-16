package com.devslopsleon.orders.services;

import com.devslopsleon.orders.core.dto.WarehouseDTO;
import com.devslopsleon.orders.core.models.company.Warehouse;

import java.util.List;

public interface IWarehouseService {

    Warehouse findWarehouseByCompanyAndCode(final String company, final String warehouse);

    WarehouseDTO findWarehouseDTOByWarehouseCode(final String warehouseCode);

    List<WarehouseDTO> listWarehouses();

}
