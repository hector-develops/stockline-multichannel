package com.devslopsleon.orders.core.dto.user;


import com.devslopsleon.orders.core.dto.address.AddressRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CustomerRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;
    private String lastName;
    @NotBlank(message = "El mail es obligatorio")
    private String mail;
    private boolean active;
    //private String channelName;

    @Valid
    private AddressRequestDTO address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public AddressRequestDTO getAddress() {
        return address;
    }

    public void setAddress(AddressRequestDTO address) {
        this.address = address;
    }
}
