package com.devslopsleon.orders.core.repository;

import com.devslopsleon.orders.core.models.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query(value= "SELECT * FROM company WHERE uid=?1 and wstatus=1", nativeQuery=true)
    Optional<Company> findCompanyByUid(String uid);

    @Query(value= "SELECT * FROM company WHERE UNIQUEID=?1 and wstatus=1", nativeQuery=true)
    Optional<Company> findCompanyByUniqueId(String uid);

    @Query(value= "SELECT * FROM company WHERE pk=?1", nativeQuery=true)
    Optional<Company> findCompanyById(Long id);
}
