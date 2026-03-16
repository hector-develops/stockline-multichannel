package com.devslopsleon.orders.core.dto.transactional.anymarket;

import java.io.Serializable;

public class ShippingAnyDTO implements Serializable {

    private String city;
    private String state;
    private String stateNameNormalized;
    private String country;
    private String countryAcronymNormalized;
    private String countryNameNormalized;
    private String address;
    private String street;
    private int number;
    private String comment;
    private int zipCode;
    private String neighborhood;
    private String receiverName;
    private String promisedShippingTime;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateNameNormalized() {
        return stateNameNormalized;
    }

    public void setStateNameNormalized(String stateNameNormalized) {
        this.stateNameNormalized = stateNameNormalized;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryAcronymNormalized() {
        return countryAcronymNormalized;
    }

    public void setCountryAcronymNormalized(String countryAcronymNormalized) {
        this.countryAcronymNormalized = countryAcronymNormalized;
    }

    public String getCountryNameNormalized() {
        return countryNameNormalized;
    }

    public void setCountryNameNormalized(String countryNameNormalized) {
        this.countryNameNormalized = countryNameNormalized;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getPromisedShippingTime() {
        return promisedShippingTime;
    }

    public void setPromisedShippingTime(String promisedShippingTime) {
        this.promisedShippingTime = promisedShippingTime;
    }
}
