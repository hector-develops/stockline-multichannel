package com.devslopsleon.orders.mappers.address;


import com.devslopsleon.orders.core.dto.address.CountryDTO;
import com.devslopsleon.orders.core.models.address.Country;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    //CountryMapper mapper = Mappers.getMapper(CountryMapper.class);

    CountryDTO toDTO(Country country);

    Country toModel(CountryDTO dto);
}
