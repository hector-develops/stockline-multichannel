package com.devslopsleon.orders.mappers.company;


import com.devslopsleon.orders.core.dto.company.CompanyDTO;
import com.devslopsleon.orders.core.dto.company.CompanyRequestDTO;
import com.devslopsleon.orders.core.models.company.Company;
import com.devslopsleon.orders.mappers.WarehouseMapper;
import com.devslopsleon.orders.mappers.address.AddressMapper;
import com.devslopsleon.orders.mappers.store.StoreNoCompanyMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = { WarehouseMapper.class, AddressMapper.class, StoreNoCompanyMapper.class })
public interface CompanyMapper {

    //CompanyMapper mapper = Mappers.getMapper(CompanyMapper.class);

    @Mapping(source = "wStatus", target = "active")
    CompanyDTO toDTO(Company entity);

    @Mapping(source = "active", target = "wStatus")
    @Mapping(target = "uniqueID", ignore = true)
    @Mapping(target = "creationTime", ignore = true)
    @Mapping(target = "modifiedTime", ignore = true)
    @Mapping(target = "address.region", ignore = true)
    Company toEntity(CompanyRequestDTO dto);

    //void updateEntityFromDto(CompanyRequestDTO dto, @MappingTarget Company entity);
}
