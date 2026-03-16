package com.devslopsleon.orders.services;

import com.devslopsleon.orders.core.dto.company.CompanyDTO;
import com.devslopsleon.orders.core.dto.company.CompanyRequestDTO;

public interface ICompanyService {

    CompanyDTO addCompany(final CompanyRequestDTO companyDTO);

    CompanyDTO findCompanyByCode(final String code);

    CompanyDTO updateCompanyByCode(final CompanyDTO companyDTO);
}
