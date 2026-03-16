package com.devslopsleon.orders.controllers;

import com.devslopsleon.orders.core.dto.product.ProductDTO;
import com.devslopsleon.orders.core.dto.product.ProductWarehousePageDTO;
import com.devslopsleon.orders.core.dto.product.ProductWarehouseStockDTO;
import com.devslopsleon.orders.core.dto.product.request.ProductRequestDTO;
import com.devslopsleon.orders.services.IProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final IProductService productService;


    @PreAuthorize("hasAnyRole('ADMINISTRATOR','MANAGEMENT','AGENT', 'SUPERADMIN')")
    @PostMapping
    public ResponseEntity<?> createProduct (@Valid @RequestBody ProductRequestDTO dto
                                            ) {
        ProductDTO p = productService.createProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(p);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR','MANAGEMENT','AGENT', 'SUPERADMIN')")
    @GetMapping("/code/{productCode}")
    public ResponseEntity<?> findProductByCompanyAndCode(@PathVariable String productCode) {

        ProductDTO p = productService.findProductByCompanyAndCode(productCode);

        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR','MANAGEMENT','AGENT', 'SUPERADMIN')")
    @GetMapping
    public Page<ProductDTO> pageProducts(
            @RequestParam(value = "q", required = false) String q,
            @PageableDefault(size = 20, sort = "modifiedTime", direction = Sort.Direction.DESC) Pageable pageable
    ){
        return productService.listPageProducts(q, pageable);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR','MANAGEMENT','AGENT', 'SUPERADMIN')")
    @GetMapping("/sku/{code}/warehouse/{warehouseCode}")
    public ProductWarehouseStockDTO findSkuByCodeAndWarehouse(@PathVariable String code,
                                                              @PathVariable String warehouseCode) {
        return productService.findWarehouseStockByWarehouseCode(code, warehouseCode);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR','MANAGEMENT','AGENT', 'SUPERADMIN')")
    @GetMapping("/warehouse/{warehouseCode}")
    public Page<ProductWarehousePageDTO> listProductsByWarehouse(
            @PathVariable String warehouseCode,
            @RequestParam(value = "q", required = false) String q,
            @PageableDefault(size = 20, sort = "modifiedTime", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return productService.listProductsByWarehouse(warehouseCode, q, pageable);
    }


    public ProductController(IProductService productService) {
        this.productService = productService;
    }
}
