package com.devslopsleon.orders.core.repository;

import com.devslopsleon.orders.core.models.product.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {

}
