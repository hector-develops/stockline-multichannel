package com.devslopsleon.orders.core.dto.product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductVariantDTO implements Serializable {

    private Long id;
    private String code;
    private String name;
    private String description;
    private BigDecimal price;
    private boolean active;
    private ColorDTO colorProduct;
    private String picture1;
    private String picture2;
    private String picture3;
    private String picture4;
    List<ProductVariantLowDTO> variantsLow = new ArrayList<>();
    private LocalDateTime creationTime;
    private LocalDateTime modifiedTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ColorDTO getColorProduct() {
        return colorProduct;
    }

    public void setColorProduct(ColorDTO colorProduct) {
        this.colorProduct = colorProduct;
    }

    public String getPicture1() {
        return picture1;
    }

    public void setPicture1(String picture1) {
        this.picture1 = picture1;
    }

    public String getPicture2() {
        return picture2;
    }

    public void setPicture2(String picture2) {
        this.picture2 = picture2;
    }

    public String getPicture3() {
        return picture3;
    }

    public void setPicture3(String picture3) {
        this.picture3 = picture3;
    }

    public String getPicture4() {
        return picture4;
    }

    public void setPicture4(String picture4) {
        this.picture4 = picture4;
    }

    public List<ProductVariantLowDTO> getVariantsLow() {
        return variantsLow;
    }

    public void setVariantsLow(List<ProductVariantLowDTO> variantsLow) {
        this.variantsLow = variantsLow;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}
