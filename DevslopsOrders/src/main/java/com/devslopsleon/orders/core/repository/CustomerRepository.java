package com.devslopsleon.orders.core.repository;

import com.devslopsleon.orders.core.models.person.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value= "SELECT * FROM customer WHERE pk=?1 AND companyid=?2", nativeQuery=true)
    Optional<Customer> findCustomerByCompanyAndId(Long pk, Long companyId);

    @Query(value= "SELECT * FROM customer WHERE mail=?1 AND companyid=?2", nativeQuery=true)
    Optional<Customer> findCustomerByCompanyAndEmail(String mail, Long companyId);

    @Query(value= "SELECT * FROM customer WHERE companyid=?1", nativeQuery=true)
    List<Customer> listCustomersByCompany(Long companyId);

    @Query(value= "SELECT * FROM customer WHERE companyid=?1", nativeQuery=true)
    Page<Customer> findCustomersPageByCompanyId(Long companyId, Pageable pageable);

    @EntityGraph(attributePaths = {"address"})
    @Query("""
    SELECT c FROM Customer c
    WHERE c.companyId = :companyId
      AND (
        LOWER(c.name) LIKE LOWER(CONCAT('%', :q, '%')) OR
        LOWER(c.lastName) LIKE LOWER(CONCAT('%', :q, '%')) OR
        LOWER(c.mail) LIKE LOWER(CONCAT('%', :q, '%')) OR
        LOWER(c.customId) LIKE LOWER(CONCAT('%', :q, '%'))
      )
  """)
    Page<Customer> searchByCompanyId(@Param("companyId") Long companyId,
                                     @Param("q") String q,
                                     Pageable pageable);
}
