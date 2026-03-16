package com.devslopsleon.orders.controllers;


import com.devslopsleon.orders.services.IStockLevelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stock")
public class StockLevelController {

    private final IStockLevelService stockLevelService;

    //TODO service to get products by company and warehouse
    @GetMapping("/{companyId}/warehouse/{warehouse}")
    public ResponseEntity<?> findProductStockByCompanyAndWarehouse(@PathVariable String companyId, @PathVariable String warehouse){

        return null;
    }

    @GetMapping("/{companyId}/product/{productCode}")
    public ResponseEntity<?> findProductStockByCompanyAndCode(@PathVariable String companyId, @PathVariable String productCode){

        return null;
    }

    public StockLevelController(IStockLevelService stockLevelService) {
        this.stockLevelService = stockLevelService;
    }
}
