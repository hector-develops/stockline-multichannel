package com.devslopsleon.orders.mappers.address;

import com.devslopsleon.orders.core.dto.AddressDTO;
import com.devslopsleon.orders.core.dto.address.AddressRequestDTO;
import com.devslopsleon.orders.core.models.address.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel= "spring", uses = { RegionMapper.class })
public interface AddressMapper {

    //AddressMapper mapper = Mappers.getMapper(AddressMapper.class);

    //@Mapping(source = "wStatus", target = "active")
    AddressDTO toDTO(Address entity);

    //@Mapping(source = "active", target = "wStatus")
    @Mapping(target = "region", ignore = true)
    Address toEntity(AddressDTO dto);

    @Mapping(source = "numberExt", target = "intNumber")
    @Mapping(source = "numberInt", target = "extNumber")
    @Mapping(target = "region", ignore = true)
    @Mapping(target = "creationTime", ignore = true)
    @Mapping(target = "modifiedTime", ignore = true)
    Address toEntityFromRequest(AddressRequestDTO dto);

}
