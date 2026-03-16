package com.devslopsleon.orders.core.dto.company;

import com.devslopsleon.orders.core.dto.AddressDTO;
import com.devslopsleon.orders.core.dto.WarehouseDTO;
import com.devslopsleon.orders.core.dto.address.AddressRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StoreRequestDTO {

    @NotBlank(message = "UID is required")
    @Size(min = 3, max = 30, message = "UID must be between 3 and 30 characters")
    private String uid;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name too long")
    private String name;

    @Size(max = 255, message = "Description too long")
    private String description;


    private boolean active;

    @Valid
    private WarehouseDTO warehouse;
    @Valid
    private AddressRequestDTO address;

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public WarehouseDTO getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(WarehouseDTO warehouse) {
        this.warehouse = warehouse;
    }

    public AddressRequestDTO getAddress() {
        return address;
    }

    public void setAddress(AddressRequestDTO address) {
        this.address = address;
    }
}
