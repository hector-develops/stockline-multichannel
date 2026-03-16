package com.devslopsleon.orders.controllers;


import com.devslopsleon.orders.core.dto.user.CustomerDTO;
import com.devslopsleon.orders.core.dto.user.CustomerRequestDTO;
import com.devslopsleon.orders.services.ICustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;


@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final ICustomerService customerService;

    // ✅ Crear customer (ADMINISTRATOR y AGENT)
    @PreAuthorize("hasAnyRole('ADMINISTRATOR','AGENT')")
    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody CustomerRequestDTO customer){

        CustomerDTO c = customerService.addCustomer(customer);

        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    // ✅ Ver detalle (ADMINISTRATOR, MANAGEMENT, AGENT)
    @PreAuthorize("hasAnyRole('ADMINISTRATOR','MANAGEMENT','AGENT')")
    @GetMapping("/{customerId}")
    public ResponseEntity<?> findCustomerById(@PathVariable String customerId){
        CustomerDTO customer = customerService.findCustomerById(customerId);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    // ✅ Listar paginado (ADMINISTRATOR, MANAGEMENT, AGENT)
    @PreAuthorize("hasAnyRole('ADMINISTRATOR','MANAGEMENT','AGENT', 'SUPERADMIN')")
    @GetMapping
    public Page<CustomerDTO> listCustomers(
            @RequestParam(value = "q", required = false) String q,
            @PageableDefault(size = 20, sort = "modifiedTime", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return customerService.listCustomersPageable(q, pageable);
    }

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }
}
