package com.devslopsleon.orders.services.impl;

import com.devslopsleon.orders.core.dto.product.ProductWarehousePageDTO;
import com.devslopsleon.orders.core.dto.product.ProductWarehouseStockDTO;
import com.devslopsleon.orders.core.dto.product.request.ProductVariantLowRequestDTO;
import com.devslopsleon.orders.core.dto.product.request.ProductVariantRequestDTO;
import com.devslopsleon.orders.core.models.company.StockLevel;
import com.devslopsleon.orders.core.models.company.Warehouse;
import com.devslopsleon.orders.services.auth.TenantContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.devslopsleon.orders.core.dto.product.ProductDTO;
import com.devslopsleon.orders.core.dto.product.request.ProductRequestDTO;
import com.devslopsleon.orders.core.models.product.*;
import com.devslopsleon.orders.core.repository.*;
import com.devslopsleon.orders.mappers.product.ProductMapper;
import com.devslopsleon.orders.services.IProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProductServiceImpl implements IProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;
    private final ColorRepository colorRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final BrandRepository brandRepository;
    private final ProductMapper productMapper;
    private final ProductVariantLowRepository productVariantLowRepository;
    private final WarehouseRepository warehouseRepository;
    private final StockLevelRepository stockLevelRepository;


    @Transactional
    @Override
    public ProductDTO createProduct(final ProductRequestDTO dto) {

        Long companyPk = TenantContext.getCompanyPk();
        if (companyPk == null) {
            throw new RuntimeException("Tenant not resolved");
        }

        String productCode = normalize(dto.getCode());
        if (productRepository.existsByCompanyIdAndCode(companyPk, productCode)) {
            throw new RuntimeException("Product code already exists: " + productCode);
        }
        Product product = new Product();
        product.setCompanyId(companyPk);
        product.setCode(productCode);
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        //product.setSku(dto.getSku());
        //product.setPrice(dto.get);
        product.setwStatus(true);
        //product.setPicture1(dto.getPicture1());

        if (dto.getUnitOfMeasure() != null) {
            UnitOfMeasure unit = unitOfMeasureRepository.findUnitByCodeAndCompany(dto.getUnitOfMeasure(), companyPk)
                    .orElseThrow(() -> new RuntimeException("Unit not found"));
            product.setUnit(unit);
        }

        if (dto.getBrand() != null) {
            BrandProduct brand = brandRepository.findBrandByCodeAndCompany(dto.getBrand(), companyPk)
                    .orElseThrow(() -> new RuntimeException("Brand not found"));
            product.setBrand(brand);
        }

        if (dto.getCategory() != null) {
            CategoryProduct category = categoryRepository.findCategoryByCode(dto.getCategory(), companyPk)
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategory(category);
        }

        Set<String> lowCodesInRequest = new HashSet<>();

        for (ProductVariantRequestDTO variantDto : dto.getVariants()) {
            ProductVariant variant = new ProductVariant();
            //variant.setCompanyId(companyPk);
            variant.setCode(normalize(variantDto.getCode()));
            variant.setName(variantDto.getName());
            variant.setDescription(variantDto.getDescription());
            //variant.setPicture1(variantDto.getPicture1());
            //variant.setPicture2(variantDto.getPicture2());
            //variant.setPicture3(variantDto.getPicture3());
            //variant.setPicture4(variantDto.getPicture4());
            variant.setwStatus(true);

            if (variantDto.getColor() != null) {
                ColorProduct color = colorRepository.findColorByCodeCompany(variantDto.getCode(), companyPk)
                        .orElseThrow(() -> new RuntimeException("Color not found"));
                variant.setColorProduct(color);
            }

            for (ProductVariantLowRequestDTO lowDto : variantDto.getVariantsLow()) {
                String lowCode = normalize(lowDto.getCode());

                if (!lowCodesInRequest.add(lowCode)) {
                    throw new RuntimeException("Duplicate SKU in request: " + lowCode);
                }

                if (productVariantLowRepository.existsByCompanyIdAndCode(companyPk, lowCode)) {
                    throw new RuntimeException("SKU already exists: " + lowCode);
                }

                ProductVariantLow low = new ProductVariantLow();
                low.setCompanyId(companyPk);
                low.setCode(lowCode);
                low.setDescription(lowDto.getDescription());
                low.setSize(lowDto.getSize());
                low.setPrice(lowDto.getPrice());
                //low.setWStatus(lowDto.isActive());
                variant.addVariantLow(low);
            }
            product.addVariant(variant);
        }

        Product saved = productRepository.save(product);
        return productMapper.toDTO(saved);
    }

    private String normalize(String value) {
        return value == null ? null : value.trim().toUpperCase(Locale.ROOT);
    }

    @Transactional(readOnly = true)
    @Override
    public ProductDTO findProductByCompanyAndCode(final String productCode) {
        Long companyId = TenantContext.getCompanyPk();
        if (companyId == null) {
            throw new RuntimeException("Tenant not resolved"); // o AccessDenied
        }
        Product p = productRepository.findByCompanyIdAndCode(companyId, normalize(productCode))
                .orElseThrow(() -> new RuntimeException("Product not found: " + productCode));
        return productMapper.toDTO(p);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ProductDTO> listPageProducts(String q, Pageable pageable) {
        Long companyPk = TenantContext.getCompanyPk();
        if (companyPk == null) throw new RuntimeException("Tenant not resolved");

        Page<Product> page = (q != null && !q.isBlank())
                ? productRepository.searchByCompanyId(companyPk, q.trim(), pageable)
                : productRepository.findByCompanyId(companyPk, pageable);

        return page.map(productMapper::toDTO);
    }

    @Override
    public ProductWarehouseStockDTO findWarehouseStockByWarehouseCode(final String code, final String warehouseCode) {
        Long companyPk = TenantContext.getCompanyPk();
        if (companyPk == null) {
            throw new RuntimeException("Tenant not resolved");
        }

        String normalizedCode = normalize(code);
        String normalizedWarehouseCode = normalize(warehouseCode);

        Warehouse warehouse = warehouseRepository.findActiveByCompanyAndCode(normalizedWarehouseCode, companyPk)
                .orElseThrow(() -> new RuntimeException("Warehouse not found: " + warehouseCode));

        ProductVariantLow sku = productVariantLowRepository
                .findByCompanyIdAndCodeWithProduct(companyPk, normalizedCode)
                .orElseThrow(() -> new RuntimeException("SKU not found: " + code));

        StockLevel stock = stockLevelRepository
                .findStockByproductAndWarehouseId(normalizedCode, warehouse.getPk(), companyPk)
                .orElseThrow(() -> new RuntimeException(
                        "Stock not found for sku " + code + " in warehouse " + warehouseCode
                ));

        ProductWarehouseStockDTO dto = new ProductWarehouseStockDTO();
        dto.setCode(sku.getCode());
        dto.setDescription(sku.getDescription());
        dto.setSize(sku.getSize());
        dto.setPrice(sku.getPrice());
        dto.setActive(sku.iswStatus());

        dto.setWarehouseId(warehouse.getPk());
        dto.setWarehouseCode(warehouse.getCode());

        dto.setAvailable(stock.getAvailable());
        dto.setReserved(stock.getReserved());
        dto.setOverselling(stock.getOverselling());

        ProductVariant variant = sku.getProductVariant();
        Product product = variant.getProduct();

        dto.setProductName(product.getName());
        dto.setVariantName(variant.getName());

        dto.setUnit(product.getUnit() != null ? product.getUnit().getCode() : null);
        dto.setBrand(product.getBrand() != null ? product.getBrand().getName() : null);
        dto.setCategory(product.getCategory() != null ? product.getCategory().getName() : null);

        return dto;
    }

    @Override
    public Page<ProductWarehousePageDTO> listProductsByWarehouse(String warehouseCode, String q, Pageable pageable) {
        Long companyPk = TenantContext.getCompanyPk();
        if (companyPk == null) {
            throw new RuntimeException("Tenant not resolved");
        }

        String normalizedWarehouse = warehouseCode.trim().toUpperCase();
        String normalizedQ = (q == null || q.isBlank()) ? null : q.trim();

        // validar warehouse activo dentro de la company
        Warehouse wh = warehouseRepository.findActiveByCompanyAndCode(normalizedWarehouse, companyPk)
                .orElseThrow(() -> new RuntimeException("Warehouse not found: " + warehouseCode));

        System.out.println("wh" + wh);

        return stockLevelRepository.findProductsByWarehouseAndSearch(
                companyPk,
                wh.getPk(),
                normalizedQ,
                pageable
        );
    }


    public ProductServiceImpl(ProductRepository productRepository,
                              ColorRepository colorRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, BrandRepository brandRepository, ProductMapper productMapper, ProductVariantLowRepository productVariantLowRepository, WarehouseRepository warehouseRepository, StockLevelRepository stockLevelRepository) {
        this.productRepository = productRepository;
        this.colorRepository = colorRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.brandRepository = brandRepository;
        this.productMapper = productMapper;
        this.productVariantLowRepository = productVariantLowRepository;
        this.warehouseRepository = warehouseRepository;
        this.stockLevelRepository = stockLevelRepository;
    }
}
