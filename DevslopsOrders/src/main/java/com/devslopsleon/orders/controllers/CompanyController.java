package com.devslopsleon.orders.controllers;


import com.devslopsleon.orders.core.dto.StoreDTO;
import com.devslopsleon.orders.core.dto.company.CompanyDTO;
import com.devslopsleon.orders.core.dto.company.CompanyRequestDTO;
import com.devslopsleon.orders.core.dto.company.SalesChannelDTO;
import com.devslopsleon.orders.core.dto.company.ShippingMethodDTO;
import com.devslopsleon.orders.services.ICompanyService;
import com.devslopsleon.orders.services.ISalesChannelService;
import com.devslopsleon.orders.services.IShippingMethodService;
import com.devslopsleon.orders.services.IStoreService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {

    private final ICompanyService companyService;
    private final IShippingMethodService shippingMethodService;
    private final ISalesChannelService salesChannelService;
    private final IStoreService storeService;

    @PreAuthorize("hasRole('SUPERADMIN')")
    @PostMapping
    public ResponseEntity<CompanyDTO> create (@Valid @RequestBody CompanyRequestDTO dto) {
        return ResponseEntity.ok(companyService.addCompany(dto));
    }

    @GetMapping("/{uniqueID}")
    public ResponseEntity<CompanyDTO> findCompanyByUniqueID(@PathVariable final String uniqueID) {
        return ResponseEntity.ok(companyService.findCompanyByCode(uniqueID));
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR','MANAGEMENT')")
    @PutMapping("/update")
    public ResponseEntity<CompanyDTO> update(@Valid @RequestBody CompanyDTO dto) {
        return ResponseEntity.ok(companyService.updateCompanyByCode(dto));
    }

    //TODO service to get stores without company redundant
    @PreAuthorize("hasRole('SUPERADMIN')")
    @GetMapping("/stores")
    public Page<StoreDTO> findStoresByCompanyID(
            @RequestParam(value = "q", required = false) String q,
            @PageableDefault(size = 20, sort = "modifiedTime", direction = Sort.Direction.DESC) Pageable pageable

    ){
        return storeService.storesList(pageable);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR','MANAGEMENT','AGENT', 'SUPERADMIN')")
    @GetMapping("/shipping-methods")
    public ResponseEntity<List<?>> findShippingMethods(){
        List<ShippingMethodDTO> shipping = shippingMethodService.listShippingMethods();
        return new ResponseEntity<>(shipping, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR','MANAGEMENT','AGENT', 'SUPERADMIN')")
    @GetMapping("/saleschannel")
    public ResponseEntity<List<?>> findSalesChannelMethods(){
        List<SalesChannelDTO> channels = salesChannelService.listSalesChannel();
        return new ResponseEntity<>(channels, HttpStatus.OK);
    }



    public CompanyController(ICompanyService companyService, IShippingMethodService shippingMethodService, ISalesChannelService salesChannelService, IStoreService storeService) {
        this.companyService = companyService;
        this.shippingMethodService = shippingMethodService;
        this.salesChannelService = salesChannelService;
        this.storeService = storeService;
    }


}
