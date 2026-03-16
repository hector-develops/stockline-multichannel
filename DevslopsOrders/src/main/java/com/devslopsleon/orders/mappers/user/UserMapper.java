package com.devslopsleon.orders.mappers.user;


import com.devslopsleon.orders.core.dto.user.UserDTO;
import com.devslopsleon.orders.core.dto.user.request.UserRequestDTO;
import com.devslopsleon.orders.core.models.person.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel= "spring", uses = {RoleMapper.class})
public interface UserMapper {

    @Mapping(source = "wStatus", target = "active")
    UserDTO toDTO(User user);

    @Mapping(source = "active", target = "wStatus")
    User toEntity(UserDTO userDTO);

    @Mapping(target = "role", ignore=true)
    User toEntityFromRequest(UserRequestDTO userRequestDTO);

}
