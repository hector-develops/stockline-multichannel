package com.devslopsleon.orders.services;

import com.devslopsleon.orders.core.dto.user.CustomerDTO;
import com.devslopsleon.orders.core.dto.user.CustomerRequestDTO;
import org.springframework.data.domain.*;

import java.util.List;

public interface ICustomerService {

    CustomerDTO addCustomer(final CustomerRequestDTO customer);

    CustomerDTO findCustomerById(final String customerId);

    CustomerDTO findCustomerById(final Long customerId);

    List<CustomerDTO> listCustomersByCompany();

    Page<CustomerDTO> listCustomersPageable(String q, Pageable pageable);


}
