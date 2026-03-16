package com.devslopsleon.orders.core.models.company;

import jakarta.persistence.*;

import java.time.LocalDateTime;



@Entity
@Table(name="warehouse",
uniqueConstraints = @UniqueConstraint(columnNames = {"COMPANY", "CODE"}))
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK")
    private Long pk;

    @Column(name = "CODE", nullable = false)
    private String code;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "WSTATUS")
    private boolean wStatus;

    @Column(name = "ISDEFAULTWH")
    private boolean isDefaultWH;

    @Column(name = "ISWAREHOUSECOMPANY")
    private boolean isWarehouseCompany;

    @Column(name = "ISWAREHOUSESTORE")
    private boolean isWarehouseStore;

    // Warehouse
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "COMPANY", nullable = false)
    private Company company;

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

    public boolean iswStatus() {
        return wStatus;
    }

    public void setwStatus(boolean wStatus) {
        this.wStatus = wStatus;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public boolean isDefaultWH() {
        return isDefaultWH;
    }

    public void setDefaultWH(boolean defaultWH) {
        isDefaultWH = defaultWH;
    }

    public boolean isWarehouseCompany() {
        return isWarehouseCompany;
    }

    public void setWarehouseCompany(boolean warehouseCompany) {
        isWarehouseCompany = warehouseCompany;
    }

    public boolean isWarehouseStore() {
        return isWarehouseStore;
    }

    public void setWarehouseStore(boolean warehouseStore) {
        isWarehouseStore = warehouseStore;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "pk=" + pk +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", wStatus=" + wStatus +
                ", creationTime=" + creationTime +
                ", modifiedTime=" + modifiedTime +
                '}';
    }
}
