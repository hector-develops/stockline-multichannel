package com.devslopsleon.orders.mappers.company;


import com.devslopsleon.orders.core.dto.company.SalesChannelDTO;
import com.devslopsleon.orders.core.models.company.SalesChannel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel= "spring")
public interface SalesChannelMapper {

    @Mapping(source = "pk", target = "id")
    @Mapping(source = "wStatus", target = "active")
    SalesChannelDTO toDTO(SalesChannel salesChannel);

    @Mapping(source = "active", target = "wStatus")
    SalesChannel toEntity(SalesChannelDTO salesChannelDTO);

    List<SalesChannelDTO> toDTO(List<SalesChannel> salesChannels);
}
