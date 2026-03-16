package com.devslopsleon.orders.core.dto;

import com.devslopsleon.orders.core.dto.address.RegionDTO;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class AddressDTO implements Serializable {

    private Long id;
    private String street;
    private String number;
    private String numberInt;
    private String numberExt;
    private String town;
    private String zipcode;
    private boolean isDefaultAddress;
    private boolean isDeliveryAddress;
    private String mobilePhone;
    private String phone;
    private String neighborhood;
    private String deliveryInstructions;
    private RegionDTO region;
    private LocalDateTime date;
    private LocalDateTime modified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getNumberInt() {
        return numberInt;
    }

    public void setNumberInt(String numberInt) {
        this.numberInt = numberInt;
    }

    public String getNumberExt() {
        return numberExt;
    }

    public void setNumberExt(String numberExt) {
        this.numberExt = numberExt;
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

    public RegionDTO getRegion() {
        return region;
    }

    public void setRegion(RegionDTO region) {
        this.region = region;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }
}
