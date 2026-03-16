package com.devslopsleon.orders.core.models.product;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(
        name = "productvariantlow",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_product_variant_low_company", columnNames = {"COMPANYID", "CODE"})
        },
        indexes = {
                @Index(name = "idx_product_variant_low_company", columnList = "COMPANYID,CODE")
        }
)
public class ProductVariantLow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK")
    private Long pk;

    @Column(name = "CODE", nullable = false)  //El vendible ya que es la talla de un color de un producto base.
    private String code;

    @Column(name = "COMPANYID", nullable = false)
    private Long companyId;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "BASEPRICE", nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @Column(name = "BARCODE")
    private String barcode;

    @Column(name = "WSTATUS")
    private boolean wStatus;

    @Column(name = "SIZE")
    private String size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCTVARIANT")
    @JsonBackReference
    private ProductVariant productVariant;

    @Column(name = "CREATIONTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime creationTime;

    @Column(name = "MODIFIEDTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime  modifiedTime;

    @PrePersist
    protected void onCreate() {
        creationTime = LocalDateTime.now();
        modifiedTime = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        modifiedTime = LocalDateTime.now();
    }

    public String getFullName(){
        return productVariant.getProduct().getName()
                + " "
                + productVariant.getName()
                + " "
                + size;
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public boolean iswStatus() {
        return wStatus;
    }

    public void setwStatus(boolean wStatus) {
        this.wStatus = wStatus;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public ProductVariant getProductVariant() {
        return productVariant;
    }

    public void setProductVariant(ProductVariant productVariant) {
        this.productVariant = productVariant;
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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
