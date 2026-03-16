package com.devslopsleon.orders.core.dto.transactional.anymarket;

import java.io.Serializable;
import java.util.Date;

public class AddressAnyDTO implements Serializable {

    private String country;
    private String state;
    private String city;
    private int zipCode;
    private String address;
    private String street;
    private int number;
    private String comment;
    private String neighborhood;
    private String receiverName;
    private Date promisedShippingTime;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
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

    public Date getPromisedShippingTime() {
        return promisedShippingTime;
    }

    public void setPromisedShippingTime(Date promisedShippingTime) {
        this.promisedShippingTime = promisedShippingTime;
    }
}
