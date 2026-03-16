package com.devslopsleon.orders.core.dto.company;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@Data
public class WarehouseRequestDTO implements Serializable {

    @NotBlank(message = "Warehouse code is required")
    @Size(min = 3, max = 15, message = "Warehouse code must be between 3 and 15 characters")
    private String code;

    @NotBlank(message = "Warehouse name is required")
    private String name;

    @Size(max = 255, message = "Description too long")
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
