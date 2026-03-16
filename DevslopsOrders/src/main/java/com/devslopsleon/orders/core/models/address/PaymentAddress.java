package com.devslopsleon.orders.core.models.address;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="paymentaddress")
public class PaymentAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK")
    private Long pk;

    @Column(name = "STREET")
    private String street;

    @Column(name = "NUMBER")
    private String streetNumber;

    @Column(name = "EXTNUMBER")
    private String streetNumberExt;

    @Column(name = "INTNUMBER")
    private String streetNumberInt;

    @Column(name = "TOWN")
    private String town;

    @Column(name = "ZIPCODE")
    private int zipcode;

    @Column(name = "DEFAULTADDRESS")
    private boolean isDefaultAddress;

    @Column(name = "ISDELIVERYADDRESS")
    private boolean isDeliveryAddress;

    @Column(name = "MOBILEPHONE")
    private String mobilePhone;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "NEIGHBOARDHOOD")
    private String neighborhood;

    @Column(name = "DELIVERYINSTRUCTION")
    private String deliveryInstructions;

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

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetNumberExt() {
        return streetNumberExt;
    }

    public void setStreetNumberExt(String streetNumberExt) {
        this.streetNumberExt = streetNumberExt;
    }

    public String getStreetNumberInt() {
        return streetNumberInt;
    }

    public void setStreetNumberInt(String streetNumberInt) {
        this.streetNumberInt = streetNumberInt;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public boolean isDefaultAddress() {
        return isDefaultAddress;
    }

    public void setDefaultAddress(boolean defaultAddress) {
        isDefaultAddress = defaultAddress;
    }

    public boolean isDeliveryAddress() {
        return isDeliveryAddress;
    }

    public void setDeliveryAddress(boolean deliveryAddress) {
        isDeliveryAddress = deliveryAddress;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
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
