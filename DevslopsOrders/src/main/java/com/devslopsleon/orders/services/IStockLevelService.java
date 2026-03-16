package com.devslopsleon.orders.services;

import com.devslopsleon.orders.core.models.company.Warehouse;

public interface IStockLevelService {

    String findStockLevelByCompanyAndWarehouse(String CompanyId, String warehouseCode);

    String findStockProductByWarehouse(String companyId, String warehouse, String productCode);

    void reserve(final String product, final Warehouse wh, final int amount, final String comment);

    void release(final String product, final Warehouse wh, final int amount, final String comment);

}
