package com.devslopsleon.orders.core.repository;

import com.devslopsleon.orders.core.models.ordersm.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    //method to validate if an order exist
    @Query(value= "SELECT * FROM orders WHERE company=?1", nativeQuery=true)
    List<Order> findOrderUniqueByUid(Long companyId, Long externalId);

    @Query(value= "SELECT * FROM orders WHERE company=?1 AND pk=?2", nativeQuery=true)
    List<Order> findOrdersByIdAndCompany(Long companyId, Long pk);

    // ✅ Find by id (tenant-safe)
    @EntityGraph(attributePaths = {"customer", "createdBy", "salesChannel"})
    @Query("""
        SELECT o FROM Order o
        WHERE o.pk = :orderPk
          AND o.company.pk = :companyPk
    """)
    Optional<Order> findByPkAndCompanyPk(@Param("orderPk") Long orderPk,
                                         @Param("companyPk") Long companyPk);

    @Query("""
      SELECT o FROM Order o
      WHERE o.company.pk = :companyPk
        AND o.creationTime >= :from
        AND o.creationTime < :to
    """)
    Page<Order> findByCompanyAndCreationTimeBetween(@Param("companyPk") Long companyPk,
                                                    @Param("from") LocalDateTime from,
                                                    @Param("to") LocalDateTime to,
                                                    Pageable pageable);



    @Query(value= "SELECT * FROM orders WHERE company=?1", nativeQuery=true)
    List<Order> findOrdersByCompanyAll(Long companyId);

    @Query(value= "SELECT * FROM orders WHERE company=?1 AND pk=?2", nativeQuery=true)
    List<Order> findOrdersByCompanyFInitFFinal(Long companyId, Long pk);



}
