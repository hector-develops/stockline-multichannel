package com.devslopsleon.orders.core.models.address;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="deliveryaddress")
public class DeliveryAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK")
    private Long pk;

    @Column(name = "STREET")
    private String street;

    @Column(name = "NUMBER")
    private String number;

    @Column(name = "TOWN")
    private String town;

    @Column(name = "ZIPCODE", nullable = false)
    private String zipcode;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "NEIGHBOARDHOOD")
    private String neighborhood;

    @Column(name = "DELIVERYINSTRUCTION")
    private String deliveryInstructions;

    @Column(name = "COMPANYID", nullable = false)
    private Long companyId;

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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getDeliveryInstructions() {
        return deliveryInstructions;
    }

    public void setDeliveryInstructions(String deliveryInstructions) {
        this.deliveryInstructions = deliveryInstructions;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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
