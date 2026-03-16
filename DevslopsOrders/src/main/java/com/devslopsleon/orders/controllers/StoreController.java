package com.devslopsleon.orders.controllers;


import com.devslopsleon.orders.core.dto.StoreDTO;
import com.devslopsleon.orders.core.dto.company.StoreNoCompanyDTO;
import com.devslopsleon.orders.core.dto.company.StoreRequestDTO;
import com.devslopsleon.orders.services.IStoreService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/{companyId}/store")
public class StoreController {

    private final IStoreService storeService;


    @PostMapping()
    public ResponseEntity<StoreDTO> create (@Valid @RequestBody StoreRequestDTO dto) {
        return ResponseEntity.ok(storeService.addStoreToCompany(dto));
    }

    @RequestMapping(value = "/{storeId}", method = RequestMethod.GET)
    public ResponseEntity<?> findStoreById(Long storeId){
        return null;
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR','MANAGEMENT','AGENT', 'SUPERADMIN')")
    @GetMapping
    public Page<StoreNoCompanyDTO> storesListPage(){
        return null;
    }

    public StoreController(IStoreService storeService) {
        this.storeService = storeService;
    }
}
