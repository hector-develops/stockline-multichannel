package com.devslopsleon.orders.mappers.store;


import com.devslopsleon.orders.core.dto.company.StoreNoCompanyDTO;
import com.devslopsleon.orders.core.models.company.Store;
import com.devslopsleon.orders.mappers.WarehouseMapper;
import com.devslopsleon.orders.mappers.address.AddressMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {WarehouseMapper.class, AddressMapper.class})
public interface StoreNoCompanyMapper {

    //@Mapping(source = "wStatus", target = "active")
    StoreNoCompanyDTO toDTO(Store s);


}
