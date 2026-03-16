package com.devslopsleon.orders.controllers;

import com.devslopsleon.orders.core.dto.user.RoleDTO;
import com.devslopsleon.orders.core.dto.user.UserDTO;
import com.devslopsleon.orders.core.dto.user.UserDeliveryManDTO;
import com.devslopsleon.orders.core.dto.user.request.UserRequestDTO;
import com.devslopsleon.orders.services.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UsersController {

    private final IUserService userService;

    @PreAuthorize("hasAnyRole('ADMINISTRATOR','MANAGEMENT', 'SUPERADMIN')")
    @PostMapping
    public ResponseEntity<?> createUserToCompany(@RequestBody UserRequestDTO user){

        UserDTO u = userService.addNewUser(user);

        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR','MANAGEMENT','AGENT', 'SUPERADMIN')")
    @GetMapping
    public Page<UserDTO> pageUsers(@RequestParam(value = "q", required = false) String q,
                                   @PageableDefault(size = 20, sort = "modifiedTime", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return userService.listUsersPageable(q, pageable);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR','MANAGEMENT','AGENT', 'SUPERADMIN')")
    @GetMapping("/roles")
    ResponseEntity<List<RoleDTO>> listRolesByCompany(){
        List<RoleDTO> roles = userService.listRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);

    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR','MANAGEMENT','AGENT', 'SUPERADMIN')")
    @GetMapping("/deliveryman")
    ResponseEntity<List<UserDeliveryManDTO>> listDeliveryManByCompany(){
        List<UserDeliveryManDTO> users = userService.users();
        return new ResponseEntity<>(users, HttpStatus.OK);

    }

    public UsersController(IUserService userService) {
        this.userService = userService;
    }
}
