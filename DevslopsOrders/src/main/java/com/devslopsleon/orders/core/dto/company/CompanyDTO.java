package com.devslopsleon.orders.core.dto.company;

import com.devslopsleon.orders.core.dto.AddressDTO;
import com.devslopsleon.orders.core.dto.WarehouseDTO;
import lombok.Data;

import java.util.List;

@Data
public class CompanyDTO {

    private Long id;

    private String uid;

    private String name;

    private String description;

    private String logo;

    private boolean active;

    private List<WarehouseDTO> warehouses;

    private AddressDTO address;

    private List<StoreNoCompanyDTO> stores;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<WarehouseDTO> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<WarehouseDTO> warehouses) {
        this.warehouses = warehouses;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public List<StoreNoCompanyDTO> getStores() {
        return stores;
    }

    public void setStores(List<StoreNoCompanyDTO> stores) {
        this.stores = stores;
    }
}
