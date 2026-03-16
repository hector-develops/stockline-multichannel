package com.devslopsleon.orders.services;

import com.devslopsleon.orders.core.dto.product.ProductDTO;
import com.devslopsleon.orders.core.dto.product.ProductWarehousePageDTO;
import com.devslopsleon.orders.core.dto.product.ProductWarehouseStockDTO;
import com.devslopsleon.orders.core.dto.product.request.ProductRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService {

    ProductDTO createProduct(final ProductRequestDTO product);

    ProductDTO findProductByCompanyAndCode(final String productCode);

    Page<ProductDTO> listPageProducts(String q, Pageable pageable);

    ProductWarehouseStockDTO findWarehouseStockByWarehouseCode(final String code, final String warehouseCode);

    Page<ProductWarehousePageDTO> listProductsByWarehouse(final String warehouseCode, String q, Pageable pageable);

}
