package com.devslopsleon.orders.core.dto.product;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductWarehouseChannelDTO implements Serializable {
    private String code;          // ProductVariantLow.code
    private String name;
    private BigDecimal price;
    private String productName;
    private String variantName;
    private String size;
    private Integer available;
    private Integer reserved;
    private String warehouseCode;
    private String salesChannelCode;
}
