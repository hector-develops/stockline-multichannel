package com.devslopsleon.orders.core.models.company;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="stocklevel")
public class StockLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK")
    private Long pk;

    @Column(name = "PRODUCTCODE", nullable = false)
    private String productCode;

    @Column(name = "AVAILABLE",nullable = false, columnDefinition = "int default 0")
    private Integer available;

    @Column(name = "RESERVED", nullable = false, columnDefinition = "int default 0")
    private Integer reserved;

    @Column(name="WAREHOUSEID", nullable = false)
    private Long warehouseId;

    @Column(name = "OVERSELLING", nullable = false, columnDefinition = "int default 0")
    private Integer overselling;

    @Column(name = "companyId", nullable = false)
    private Long companyId;

    @Version // Optimistic Lock
    @Column(name = "VERSION")
    private Integer version = 0;

    @Column(name = "CREATIONTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime creationTime;

    @Column(name = "MODIFIEDTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedTime;

    @PrePersist
    protected void onCreate() {
        creationTime = LocalDateTime.now();
        modifiedTime = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        modifiedTime = LocalDateTime.now();
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Integer getReserved() {
        return reserved;
    }

    public void setReserved(Integer reserved) {
        this.reserved = reserved;
    }

    public Integer getOverselling() {
        return overselling;
    }

    public void setOverselling(Integer overselling) {
        this.overselling = overselling;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }
}
