package com.devslopsleon.orders.core.dto.address;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@Data
public class AddressRequestDTO implements Serializable {

    @NotBlank(message = "Street is required")
    private String street;

    @NotBlank(message = "Street number is required")
    private String number;

    private String numberInt;
    private String numberExt;

    @NotBlank(message = "Town is required")
    private String town;

    @Pattern(regexp = "^[0-9]{4,5}$", message = "Zip code must be 4–5 digits")
    //@Size(min = 4, max = 5, message = "5")
    private String zipcode;

    private boolean isDefaultAddress=false;
    private boolean isDeliveryAddress=false;

    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile phone must be 10 digits")
    private String mobilePhone;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
    private String phone;

    private String neighborhood;

    private String comments;

    @Size(max = 255, message = "Delivery instructions too long")
    private String deliveryInstructions;

    @NotBlank(message = "Region is required")
    private String region;

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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDeliveryInstructions() {
        return deliveryInstructions;
    }

    public void setDeliveryInstructions(String deliveryInstructions) {
        this.deliveryInstructions = deliveryInstructions;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
