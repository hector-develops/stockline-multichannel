package com.devslopsleon.orders.core.models.address;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK")
    private Long id;

    @Column(name = "UID", unique = true, nullable = false) //ISO CODE like MX
    private String code;

    @Column(name = "NAME")
    private String name;

    @Column(name = "WSTATUS")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean iswStatus() {
        return wStatus;
    }

    public void setwStatus(boolean wStatus) {
        this.wStatus = wStatus;
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
