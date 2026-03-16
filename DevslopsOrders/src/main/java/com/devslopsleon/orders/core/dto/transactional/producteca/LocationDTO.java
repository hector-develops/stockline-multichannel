package com.devslopsleon.orders.core.dto.transactional.producteca;

import java.io.Serializable;

public class LocationDTO implements Serializable {

    private String StreetName;
    private int StreetNumber;
    private String AddressNotes;
    private String State;
    private String City;
    private String Neighborhood;
    private int ZipCode;

    public String getStreetName() {
        return StreetName;
    }

    public void setStreetName(String streetName) {
        StreetName = streetName;
    }

    public int getStreetNumber() {
        return StreetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        StreetNumber = streetNumber;
    }

    public String getAddressNotes() {
        return AddressNotes;
    }

    public void setAddressNotes(String addressNotes) {
        AddressNotes = addressNotes;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getNeighborhood() {
        return Neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        Neighborhood = neighborhood;
    }

    public int getZipCode() {
        return ZipCode;
    }

    public void setZipCode(int zipCode) {
        ZipCode = zipCode;
    }
}
