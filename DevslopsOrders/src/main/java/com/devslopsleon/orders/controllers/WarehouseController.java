package com.devslopsleon.orders.controllers;


import com.devslopsleon.orders.core.dto.WarehouseDTO;
import com.devslopsleon.orders.core.dto.user.RoleDTO;
import com.devslopsleon.orders.services.IWarehouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/warehouse")
public class WarehouseController {

    private static final Logger log = LoggerFactory.getLogger(WarehouseController.class);
    private final IWarehouseService warehouseService;

    @PreAuthorize("hasAnyRole('ADMINISTRATOR','MANAGEMENT','AGENT', 'SUPERADMIN')")
    @GetMapping
    public ResponseEntity<List<?>> getWarehouses(){
        List<WarehouseDTO> warehouses = warehouseService.listWarehouses();
        return new ResponseEntity<>(warehouses, HttpStatus.OK);
    }

    public WarehouseController(IWarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }
}
