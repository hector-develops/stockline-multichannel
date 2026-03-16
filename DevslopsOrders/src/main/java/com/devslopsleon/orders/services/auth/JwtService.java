package com.devslopsleon.orders.services.auth;


import com.devslopsleon.orders.core.models.person.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JwtService {

    // Ponlo en application.yml como base64 o string largo
    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.access-minutes:15}")
    private long accessMinutes;

    public String generateAccessToken(User user) {
        Instant now = Instant.now();
        Instant exp = now.plus(accessMinutes, ChronoUnit.MINUTES);

        return Jwts.builder()
                .subject(String.valueOf(user.getId()))
                .issuedAt(Date.from(now))
                .expiration(Date.from(exp))
                .claim("companyPk", user.getCompanyId())
                .claim("role", user.getRole().getCode()) // ej: ADMIN, AGENT
                .claim("mail", user.getMail())
                .signWith(getKey())
                .compact();
    }

    public Claims parse(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
}
