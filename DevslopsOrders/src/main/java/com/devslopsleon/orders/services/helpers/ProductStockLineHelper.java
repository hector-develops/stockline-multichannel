package com.devslopsleon.orders.services.helpers;


import com.devslopsleon.orders.core.models.product.Product;
import com.devslopsleon.orders.core.repository.CategoryRepository;
import com.devslopsleon.orders.core.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductStockLineHelper {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public boolean productExist(final String productCode, final Long companyId){
        Optional<Product> p = productRepository.findProductByCodeAndCompanyId(productCode, companyId);
        return p.isEmpty();
    }

    public ProductStockLineHelper(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
}
