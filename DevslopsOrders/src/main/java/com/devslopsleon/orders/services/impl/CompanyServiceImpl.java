package com.devslopsleon.orders.services.impl;

import com.devslopsleon.orders.core.dto.company.CompanyDTO;
import com.devslopsleon.orders.core.dto.company.CompanyRequestDTO;
import com.devslopsleon.orders.core.dto.address.RegionDTO;
import com.devslopsleon.orders.core.models.address.Region;
import com.devslopsleon.orders.core.models.company.Company;
import com.devslopsleon.orders.core.models.company.Warehouse;
import com.devslopsleon.orders.core.repository.CompanyRepository;
import com.devslopsleon.orders.core.repository.RegionRepository;
import com.devslopsleon.orders.dexeptions.CompanyDuplicateFoundException;
import com.devslopsleon.orders.dexeptions.CompanyNotFoundException;
import com.devslopsleon.orders.mappers.company.CompanyMapper;
import com.devslopsleon.orders.services.ICompanyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyServiceImpl implements ICompanyService {

    private final CompanyRepository companyRepository;
    private final RegionRepository regionRepository;
    private final CompanyMapper companyMapper;

    private static final String ALLOWED_CHARACTERS = "0123456789ABCDEFGHJKLMNOPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";
    private static final int CODE_LENGTH = 16;

    @Override
    @Transactional
    public CompanyDTO addCompany(CompanyRequestDTO companyDTO) {
        // validate if company exist
        String uuidCompany = UUID.randomUUID().toString();
        String uuidCode = companyDTO.getUid().toUpperCase();
        if(companyRepository.findCompanyByUid(companyDTO.getUid()).isPresent()){
            throw new CompanyDuplicateFoundException("The company ID already exists!");
        }
        // validate if region exist
        Region region = regionRepository.findRegionByCodeAndStatus(companyDTO.getAddress().getRegion()).orElseThrow(
                () -> new RuntimeException("Region not found")
        );
        Company entity = companyMapper.toEntity(companyDTO);
        entity.setUid(companyDTO.getUid().toUpperCase(Locale.ROOT));
        String companyUniqueID = this.createUniqueIdCompany();
        entity.setUniqueID(companyUniqueID);
        entity.getAddress().setRegion(region);
        // 4) Warehouses
        if (entity.getWarehouses() == null) {
            entity.setWarehouses(new ArrayList<>());
        }
        if (entity.getWarehouses().isEmpty()) {
            Warehouse wh = new Warehouse();
            wh.setCode("default");
            wh.setName("default");
            wh.setDefaultWH(true);
            wh.setWarehouseCompany(true);
            wh.setWarehouseStore(false);
            wh.setCompany(entity);
            entity.getWarehouses().add(wh);
        } else {
            entity.getWarehouses().forEach(w -> w.setCompany(entity));
        }
        entity.getWarehouses().forEach(w -> w.setCode(w.getCode().trim().toUpperCase()));
        Company companySaved = companyRepository.save(entity);

        //CompanyMapper.mapper.toDTO(companySaved);
        return companyMapper.toDTO(companySaved);
    }

    @Override
    public CompanyDTO findCompanyByCode(final String code) {

        Optional<Company> existCompany = companyRepository.findCompanyByUniqueId(code);
        if(existCompany.isEmpty()){
            throw new CompanyNotFoundException("Company with ID:" + code.concat(" no exist! or is not active"));
        }
        return companyMapper.toDTO(existCompany.get());
    }

    @Override
    public CompanyDTO updateCompanyByCode(CompanyDTO companyDTO) {
        return null;
    }

    private String createUniqueIdCompany(){
        SecureRandom random = new SecureRandom();
        while (true) {
            StringBuilder code = new StringBuilder(CODE_LENGTH);
            for (int i = 0; i < CODE_LENGTH; i++) {
                code.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
            }
            if (companyRepository.findCompanyByUniqueId(code.toString()).isEmpty()) {
                return code.toString();
            }
        }
    };

    public CompanyServiceImpl(CompanyRepository companyRepository, RegionRepository regionRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.regionRepository = regionRepository;
        this.companyMapper = companyMapper;
    }

}
