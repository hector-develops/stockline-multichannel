package com.devslopsleon.orders.mappers.user;


import com.devslopsleon.orders.core.dto.user.CustomerDTO;
import com.devslopsleon.orders.core.dto.user.CustomerRequestDTO;
import com.devslopsleon.orders.core.models.person.Customer;
import com.devslopsleon.orders.mappers.address.AddressMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel= "spring", uses = {AddressMapper.class})
public interface CustomerMapper {


    //CustomerMapper mapper = Mappers.getMapper(CustomerMapper.class);

    @Mapping(source = "pk", target = "id")
    @Mapping(source = "wStatus", target = "active")
    //@Mapping(source = "channel", target = "channelName")
    @Mapping(target = "address.region", ignore = true)
    CustomerDTO ToCustomerDTO(Customer customer);

    @Mapping(target = "address.region", ignore = true)
    Customer ToCustomer(CustomerDTO customerDTO);

    @Mapping(target = "address.region", ignore = true)
    //@Mapping(source = "channelName", target = "channel")
    Customer ToCustomer(CustomerRequestDTO customerRequestDTO);

    List<CustomerDTO> customersListDTO(List<Customer>customers);

}
