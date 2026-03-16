package com.devslopsleon.orders.core.models.address;


import com.devslopsleon.orders.core.models.company.Company;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK")
    private Long pk;

    @Column(name = "STREET")
    private String street;

    @Column(name = "NUMBER")
    private String number;

    @Column(name = "EXTNUMBER")
    private String extNumber;

    @Column(name = "INTNUMBER")
    private String intNumber;

    @Column(name = "TOWN")
    private String town;

    @Column(name = "ZIPCODE")
    private String zipcode;

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

    @Column(name = "COMMENTS")
    private String comments;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="REGION")
    //@JsonIgnoreProperties("hibernateLazyInitializer")
    private Region region;

    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
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

    public String getExtNumber() {
        return extNumber;
    }

    public void setExtNumber(String extNumber) {
        this.extNumber = extNumber;
    }

    public String getIntNumber() {
        return intNumber;
    }

    public void setIntNumber(String intNumber) {
        this.intNumber = intNumber;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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
