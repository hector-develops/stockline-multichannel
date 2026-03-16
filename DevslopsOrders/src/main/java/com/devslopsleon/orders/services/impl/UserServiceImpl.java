package com.devslopsleon.orders.services.impl;

import com.devslopsleon.orders.core.dto.user.RoleDTO;
import com.devslopsleon.orders.core.dto.user.UserDTO;
import com.devslopsleon.orders.core.dto.user.UserDeliveryManDTO;
import com.devslopsleon.orders.core.dto.user.request.UserRequestDTO;
import com.devslopsleon.orders.core.models.person.User;
import com.devslopsleon.orders.core.models.person.UserRole;
import com.devslopsleon.orders.core.repository.UserRepository;
import com.devslopsleon.orders.core.repository.UserRoleRepository;
import com.devslopsleon.orders.mappers.user.DeliveryManMapper;
import com.devslopsleon.orders.mappers.user.RoleMapper;
import com.devslopsleon.orders.mappers.user.UserMapper;
import com.devslopsleon.orders.services.IUserService;
import com.devslopsleon.orders.services.auth.TenantContext;
import com.devslopsleon.orders.services.helpers.UserStockLineHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserRoleRepository  userRoleRepository;
    private final RoleMapper roleMapper;
    private final UserStockLineHelper  userStockLineHelper;
    private final DeliveryManMapper deliveryManMapper;


    @Override
    public UserDTO addNewUser(UserRequestDTO userRequestDTO) {
        Long companyPk = TenantContext.getCompanyPk();
        if (companyPk == null) {
            throw new RuntimeException("Tenant not resolved"); // o AccessDenied
        }

        if(userStockLineHelper.existUserRegistered(userRequestDTO.getMail(), companyPk)){

        }

        User u = userMapper.toEntityFromRequest(userRequestDTO);
        u.setCompanyId(companyPk);

        return null;
    }

    @Override
    public UserDTO getUser(String username) {
        Long companyPk = TenantContext.getCompanyPk();
        if (companyPk == null) {
            throw new RuntimeException("Tenant not resolved"); // o AccessDenied
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserDTO> listUsersPageable(String q, Pageable pageable) {
        Long companyPk = TenantContext.getCompanyPk();
        if (companyPk == null) {
            throw new RuntimeException("Tenant not resolved"); // o AccessDenied
        }
        Page<User>  page;
        if (q != null && !q.trim().isEmpty()){
            page = userRepository.searchByCompanyId(companyPk, q.trim(), pageable);
        }else {
            page = userRepository.findUsersPageByCompanyId(companyPk, pageable);
        }
        return page.map(userMapper::toDTO);
    }

    @Override
    public List<RoleDTO> listRoles() {
        Long companyPk = TenantContext.getCompanyPk();
        if (companyPk == null) {
            throw new RuntimeException("Tenant not resolved"); // o AccessDenied
        }
        List<UserRole> roles = userRoleRepository.findByRolesByCompanyId(companyPk);
        return roleMapper.toDTO(roles);
    }

    @Override
    public List<UserDeliveryManDTO> users() {
        Long id = 6L;
        Long companyId = TenantContext.getCompanyPk();
        if (companyId == null) {
            throw new RuntimeException("Tenant not resolved"); // o AccessDenied
        }
        List<User> users = userRepository.findUsersDeliveryManByCompany(companyId);
        return deliveryManMapper.usersToUserDeliveryManDTOs(users);
    }

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, UserRoleRepository userRoleRepository, RoleMapper roleMapper, UserStockLineHelper userStockLineHelper, DeliveryManMapper deliveryManMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userRoleRepository = userRoleRepository;
        this.roleMapper = roleMapper;
        this.userStockLineHelper = userStockLineHelper;
        this.deliveryManMapper = deliveryManMapper;
    }
}
