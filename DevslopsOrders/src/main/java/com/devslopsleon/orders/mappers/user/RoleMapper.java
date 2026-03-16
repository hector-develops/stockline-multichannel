package com.devslopsleon.orders.mappers.user;


import com.devslopsleon.orders.core.dto.user.RoleDTO;
import com.devslopsleon.orders.core.models.person.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {


    @Mapping(source = "pk", target = "id")
    @Mapping(source = "code", target = "role")
    @Mapping(source = "wStatus", target = "active")
    RoleDTO toDTO(UserRole userRole);


    List<RoleDTO> toDTO(List<UserRole> userRoles);

    UserRole toModel(RoleDTO roleDTO);
}
