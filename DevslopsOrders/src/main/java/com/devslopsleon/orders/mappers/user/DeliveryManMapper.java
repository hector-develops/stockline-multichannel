package com.devslopsleon.orders.mappers.user;


import com.devslopsleon.orders.core.dto.user.UserDeliveryManDTO;
import com.devslopsleon.orders.core.models.person.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel= "spring", uses = {RoleMapper.class})
public interface DeliveryManMapper {

    @Mapping(source = "wStatus", target = "active")
    @Mapping(target = "role", expression = "java(user.getRole() != null ? user.getRole().getCode() : null)")
    UserDeliveryManDTO userToUserDeliveryManDTO(User user);


    List<UserDeliveryManDTO> usersToUserDeliveryManDTOs(List<User> users);
}
