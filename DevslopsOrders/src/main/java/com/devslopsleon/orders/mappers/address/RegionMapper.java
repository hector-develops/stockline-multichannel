package com.devslopsleon.orders.mappers.address;

import com.devslopsleon.orders.core.dto.address.RegionDTO;
import com.devslopsleon.orders.core.models.address.Region;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel= "spring", uses = {CountryMapper.class})
public interface RegionMapper {

    //RegionMapper mapper = Mappers.getMapper(RegionMapper.class);

    RegionDTO toDTO(Region region);

    Region toEntity(RegionDTO regionDTO);

}
