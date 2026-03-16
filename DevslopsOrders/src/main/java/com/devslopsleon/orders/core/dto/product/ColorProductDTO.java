package com.devslopsleon.orders.core.dto.product;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ColorProductDTO {

    private Long id;
    private String code;
    private String name;

}
