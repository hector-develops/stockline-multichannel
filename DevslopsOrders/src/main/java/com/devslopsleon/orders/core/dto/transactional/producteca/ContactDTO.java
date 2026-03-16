package com.devslopsleon.orders.core.dto.transactional.producteca;

import java.io.Serializable;

public class ContactDTO implements Serializable {
    private String Name;
    private String ContactPerson;
    private String Mail;
    private String PhoneNumber;
    private String Type;
    private int Id;
    private String TaxId;
    private String BillingInfo;
    private String Notes;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getContactPerson() {
        return ContactPerson;
    }

    public void setContactPerson(String contactPerson) {
        ContactPerson = contactPerson;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTaxId() {
        return TaxId;
    }

    public void setTaxId(String taxId) {
        TaxId = taxId;
    }

    public String getBillingInfo() {
        return BillingInfo;
    }

    public void setBillingInfo(String billingInfo) {
        BillingInfo = billingInfo;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }
}
