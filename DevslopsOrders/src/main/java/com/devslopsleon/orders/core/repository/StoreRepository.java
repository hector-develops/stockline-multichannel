package com.devslopsleon.orders.core.repository;

import com.devslopsleon.orders.core.models.company.Store;
import com.devslopsleon.orders.core.models.person.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query(value= "SELECT * FROM store WHERE uid=?1 and company=?2", nativeQuery=true)
    Optional<Store> existStoreByCompany(String uid, Long companyId);

    @Query(value= "SELECT * FROM store WHERE company=?1", nativeQuery=true)
    Page<Store> findStoresPageByCompanyId(Long companyId, Pageable pageable);

}
