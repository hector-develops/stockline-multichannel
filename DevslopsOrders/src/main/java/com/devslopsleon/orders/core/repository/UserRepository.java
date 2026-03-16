package com.devslopsleon.orders.core.repository;

import com.devslopsleon.orders.core.models.person.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value= "SELECT * FROM users WHERE mail=?1 and companyid=?2 and wstatus=1", nativeQuery=true)
    Optional<User> findUserByUsernameAndCompanyAndStatus(final String username, final Long company);

    @Query(value= "SELECT * FROM users WHERE mail=?1 and companyid=?2 and wstatus=1", nativeQuery=true)
    Optional<User> findByCompanyIdAndMailAndWStatusTrue(final String mail, final Long company);

    @Query(value= "SELECT * FROM users WHERE companyid=?1 and wstatus=1 and role=6", nativeQuery=true)
    List<User> findUsersDeliveryManByCompany(final Long company);

    @Query("""
      SELECT u FROM User u
      JOIN FETCH u.role r
      WHERE u.companyId = :companyId
        AND u.mail = :mail
        AND u.wStatus = true
    """)
    Optional<User> findActiveByCompanyIdAndMailWithRole(@Param("mail") String mail, @Param("companyId") Long companyId);

    @Query("""
      SELECT u FROM User u
      WHERE u.mail = :mail
        AND u.companyId = :companyPk
        AND u.wStatus = true
    """)
    Optional<User> findUserByMailAndCompanyAndStatus(@Param("mail") String mail,
                                                     @Param("companyPk") Long companyPk);

    @Query(value= "SELECT * FROM users WHERE companyid=?1", nativeQuery=true)
    Page<User> findUsersPageByCompanyId(Long companyId, Pageable pageable);

    @EntityGraph()
    @Query("""
    SELECT u FROM User u
    WHERE u.companyId = :companyId
      AND (
        LOWER(u.name) LIKE LOWER(CONCAT('%', :q, '%')) OR
        LOWER(u.mail) LIKE LOWER(CONCAT('%', :q, '%')) OR
        LOWER(u.customId) LIKE LOWER(CONCAT('%', :q, '%'))
      )
  """)
    Page<User> searchByCompanyId(@Param("companyId") Long companyId,
                                     @Param("q") String q,
                                     Pageable pageable);


}
