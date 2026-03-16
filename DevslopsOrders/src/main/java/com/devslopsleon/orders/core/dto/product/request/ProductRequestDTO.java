package com.devslopsleon.orders.core.dto.product.request;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;

public class ProductRequestDTO implements Serializable {

    @NotBlank(message = "Product code is required")
    private String code;
    @NotBlank(message = "Product name is required")
    private String name;

    private String description;
    private Boolean active = true;
    private String unitOfMeasure;
    private String category;
    private String brand;
    private List<ProductVariantRequestDTO> variants;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<ProductVariantRequestDTO> getVariants() {
        return variants;
    }

    public void setVariants(List<ProductVariantRequestDTO> variants) {
        this.variants = variants;
    }
}
