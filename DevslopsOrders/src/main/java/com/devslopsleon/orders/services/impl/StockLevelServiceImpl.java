package com.devslopsleon.orders.services.impl;


import com.devslopsleon.orders.core.models.company.StockLevel;
import com.devslopsleon.orders.core.models.company.Warehouse;
import com.devslopsleon.orders.core.repository.CompanyRepository;
import com.devslopsleon.orders.core.repository.StockLevelRepository;
import com.devslopsleon.orders.core.repository.StoreRepository;
import com.devslopsleon.orders.dexeptions.InsufficientStock;
import com.devslopsleon.orders.dexeptions.StockUnavailableException;
import com.devslopsleon.orders.services.IStockLevelService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockLevelServiceImpl implements IStockLevelService {

    private final StockLevelRepository stockLevelRepository;
    private final CompanyRepository companyRepository;
    private final StoreRepository storeRepository;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public String findStockLevelByCompanyAndWarehouse(String CompanyId, String warehouseCode) {
        return "";
    }

    @Override
    public String findStockProductByWarehouse(String companyId, String warehouse, String productCode) {
        return "";
    }

    @Override
    public void reserve(String product, Warehouse wh, int amount, String comment) {
        if(amount <=0){
            throw new StockUnavailableException("amount must be greater than zero");
        }else {
            StockLevel sl = stockLevelRepository.findStockByproductAndWarehouseId(product, wh.getPk(), 0L).orElseThrow(
                    () -> new RuntimeException("Product not found")
            );
            int current = (sl.getAvailable() - sl.getReserved());
            if(amount > current){
                throw new InsufficientStock("Insufficient stock for product");
            }
            int newReserved = sl.getReserved() + amount;

        }
    }

    @Override
    public void release(String product, Warehouse wh, int amount, String comment) {

    }

    public StockLevelServiceImpl(StockLevelRepository stockLevelRepository, CompanyRepository companyRepository,
                                 StoreRepository storeRepository, JdbcTemplate jdbcTemplate) {
        this.stockLevelRepository = stockLevelRepository;
        this.companyRepository = companyRepository;
        this.storeRepository = storeRepository;
        this.jdbcTemplate = jdbcTemplate;
    }
}
