package com.devslopsleon.orders.core.models.company;


import com.devslopsleon.orders.core.models.address.Address;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name="store",
        uniqueConstraints = @UniqueConstraint(columnNames = {"COMPANY", "UID"})
)
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK")
    private Long pk;

    @Column(name = "UID", nullable = false)
    private String uid;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "LOGO")
    private String logo;


    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL, optional = false)
    @JoinColumn(name="WAREHOUSE", nullable=false, unique=true, referencedColumnName = "PK")
    private Warehouse warehouse;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "ADDRESS", referencedColumnName = "PK")
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company")
    @JsonBackReference
    private Company company;

    @Column(name = "STATUSSTORE")
    private boolean wStatus;

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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public void setModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public boolean iswStatus() {
        return wStatus;
    }

    public void setwStatus(boolean wStatus) {
        this.wStatus = wStatus;
    }

}
