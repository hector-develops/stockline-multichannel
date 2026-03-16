package com.devslopsleon.orders.core.repository;

import com.devslopsleon.orders.core.models.person.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    @Query(value= "SELECT * FROM refresh_token WHERE token_hash=?1", nativeQuery=true)
    Optional<RefreshToken> findByTokenHashAndRevokedFalse(String tokenHash);

    @Query(value= "SELECT * FROM refresh_token WHERE user_id=?1", nativeQuery=true)
    void deleteByUserPk(Long userPk);
}
