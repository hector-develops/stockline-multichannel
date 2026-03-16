package com.devslopsleon.orders.core.repository;

import com.devslopsleon.orders.core.models.person.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    @Query(value= "SELECT * FROM roles WHERE companyid=?1", nativeQuery=true)
    List<UserRole> findByRolesByCompanyId(Long companyId);
}
