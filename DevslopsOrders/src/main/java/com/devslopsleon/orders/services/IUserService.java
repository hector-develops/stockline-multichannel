package com.devslopsleon.orders.services;

import com.devslopsleon.orders.core.dto.user.RoleDTO;
import com.devslopsleon.orders.core.dto.user.UserDTO;
import com.devslopsleon.orders.core.dto.user.UserDeliveryManDTO;
import com.devslopsleon.orders.core.dto.user.request.UserRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {

    UserDTO addNewUser(final UserRequestDTO userRequestDTO);

    UserDTO getUser(final String username);

    Page<UserDTO> listUsersPageable(String q, Pageable pageable);

    List<RoleDTO> listRoles();

    List<UserDeliveryManDTO> users();

}
