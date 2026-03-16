package com.devslopsleon.orders.services.impl;


import com.devslopsleon.orders.core.dto.user.CustomerDTO;
import com.devslopsleon.orders.core.dto.user.CustomerRequestDTO;
import com.devslopsleon.orders.core.models.address.Region;
import com.devslopsleon.orders.core.models.company.Company;
import com.devslopsleon.orders.core.models.person.Customer;
import com.devslopsleon.orders.core.repository.CompanyRepository;
import com.devslopsleon.orders.core.repository.CustomerRepository;
import com.devslopsleon.orders.core.repository.RegionRepository;
import com.devslopsleon.orders.dexeptions.CompanyDuplicateFoundException;
import com.devslopsleon.orders.dexeptions.CompanyNotFoundException;
import com.devslopsleon.orders.mappers.user.CustomerMapper;
import com.devslopsleon.orders.services.ICustomerService;
import com.devslopsleon.orders.services.auth.TenantContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final CompanyRepository companyRepository;
    private final RegionRepository regionRepository;
    private final CustomerMapper customerMapper;
    private static final String SALES_CHANNEL ="stockline";



    @Override
    public CustomerDTO addCustomer(CustomerRequestDTO customer) {
        Customer c ;
        CustomerDTO cDTO ;
        Long companyId = requireTenant();
        Company company = companyRepository.findCompanyById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        //validate Region code if exist
        Region region = regionRepository.findRegionByCodeAndStatus(customer.getAddress().getRegion()).orElseThrow(
                () -> new RuntimeException("Region not found")
        );
        c = customerMapper.ToCustomer(customer);
        c.setCompanyId(companyId);
        c.setCustomId(createCustomCustomerId());
        c.setChannel(SALES_CHANNEL);
        if(c.getAddress() != null){
            c.getAddress().setCompany(company);
            c.getAddress().setRegion(region);
        }
        Customer cSaved = customerRepository.save(c);
        cDTO = customerMapper.ToCustomerDTO(cSaved);
        return cDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDTO findCustomerById(final String customerId) {
        Long companyId = TenantContext.getCompanyPk();
        if (companyId == null) {
            throw new RuntimeException("Tenant not resolved"); // o AccessDenied
        }
        Customer c = customerRepository.findCustomerByCompanyAndEmail(customerId, companyId).orElseThrow(
                ()-> new RuntimeException("Customer not found"));

        return customerMapper.ToCustomerDTO(c);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDTO findCustomerById(Long customerId) {
        Long companyPk = TenantContext.getCompanyPk();
        if (companyPk == null) {
            throw new RuntimeException("Tenant not resolved"); // o AccessDenied
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDTO> listCustomersByCompany() {
        Long companyPk = TenantContext.getCompanyPk();
        if (companyPk == null) {
            throw new RuntimeException("Tenant not resolved"); // o AccessDenied
        }
        List<Customer> customers = customerRepository.listCustomersByCompany(companyPk);
        return customerMapper.customersListDTO(customers);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CustomerDTO> listCustomersPageable(String q, Pageable pageable) {

        Long companyPk = TenantContext.getCompanyPk();
        if (companyPk == null) {
            throw new RuntimeException("Tenant not resolved"); // o AccessDenied
        }
        Page<Customer> page;
        if (q != null && !q.trim().isEmpty()){
            page = customerRepository.searchByCompanyId(companyPk, q.trim(), pageable);
        }else {
            page = customerRepository.findCustomersPageByCompanyId(companyPk, pageable);
        }
        return page.map(customerMapper::ToCustomerDTO);
    }

    private String createCustomCustomerId(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    private Long requireTenant() {
        Long companyPk = TenantContext.getCompanyPk();
        if (companyPk == null) throw new RuntimeException("Tenant not resolved");
        return companyPk;
    }

    public CustomerServiceImpl(CustomerRepository customerRepository, CompanyRepository companyRepository, RegionRepository regionRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.companyRepository = companyRepository;
        this.regionRepository = regionRepository;
        this.customerMapper = customerMapper;
    }
}
