package com.devslopsleon.orders.mappers.company;


import com.devslopsleon.orders.mappers.WarehouseMapper;
import com.devslopsleon.orders.mappers.address.AddressMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { WarehouseMapper.class, AddressMapper.class })
public interface CompanyNoStoresMapper {

}
