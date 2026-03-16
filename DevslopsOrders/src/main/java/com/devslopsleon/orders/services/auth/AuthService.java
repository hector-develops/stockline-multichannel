package com.devslopsleon.orders.services.auth;


import com.devslopsleon.orders.core.dto.LoginRequest;
import com.devslopsleon.orders.core.dto.TokenResponse;
import com.devslopsleon.orders.core.models.company.Company;
import com.devslopsleon.orders.core.models.person.RefreshToken;
import com.devslopsleon.orders.core.models.person.User;
import com.devslopsleon.orders.core.repository.CompanyRepository;
import com.devslopsleon.orders.core.repository.RefreshTokenRepository;
import com.devslopsleon.orders.core.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HexFormat;
import java.util.Locale;
import java.util.UUID;

@Service
public class AuthService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshRepo;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    @Value("${security.jwt.refresh-days:14}")
    private long refreshDays;

    public TokenResponse login(LoginRequest req, HttpServletResponse resp) {

        final String uidCode = req.companyUniqueId();
        Company company = companyRepository.findCompanyByUid(uidCode)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        //TODO mejore por findbyUuuid is more friendly
        /*
        Company company = companyRepository.findCompanyByUniqueId(req.companyUniqueId())
                .orElseThrow(() -> new RuntimeException("Company not found"));

         */

        User user = userRepository.findByCompanyIdAndMailAndWStatusTrue(req.mail(), company.getPk())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!encoder.matches(req.password(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String access = jwtService.generateAccessToken(user);

        // refresh token raw (random) + store hash
        String refreshRaw = UUID.randomUUID() + "-" + UUID.randomUUID();
        String refreshHash = sha256(refreshRaw);
        RefreshToken rt = new RefreshToken();
        rt.setUser(user);
        rt.setTokenHash(refreshHash);
        rt.setExpiresAt(LocalDateTime.now().plusDays(refreshDays));
        refreshRepo.save(rt);

        Cookie cookie = new Cookie("refresh_token", refreshRaw);
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // en prod true
        cookie.setPath("/auth");
        cookie.setMaxAge((int) Duration.ofDays(refreshDays).getSeconds());
        resp.addCookie(cookie);

        return new TokenResponse(access);
    }

    public TokenResponse refresh(String refreshRaw, HttpServletResponse resp) {
        if (refreshRaw == null || refreshRaw.isBlank()) throw new RuntimeException("Missing refresh token");

        String hash = sha256(refreshRaw);
        RefreshToken rt = refreshRepo.findByTokenHashAndRevokedFalse(hash)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (rt.getExpiresAt().isBefore(LocalDateTime.now())) {
            rt.setRevoked(true);
            refreshRepo.save(rt);
            throw new RuntimeException("Refresh expired");
        }

        // rotate refresh token
        rt.setRevoked(true);
        refreshRepo.save(rt);
        User user = rt.getUser();
        String access = jwtService.generateAccessToken(user);

        String newRaw = UUID.randomUUID() + "-" + UUID.randomUUID();
        String newHash = sha256(newRaw);

        RefreshToken newRt = new RefreshToken();
        newRt.setUser(user);
        newRt.setTokenHash(newHash);
        newRt.setExpiresAt(LocalDateTime.now().plusDays(refreshDays));
        refreshRepo.save(newRt);
        Cookie cookie = new Cookie("refresh_token", newRaw);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/auth");
        cookie.setMaxAge((int) Duration.ofDays(refreshDays).getSeconds());
        resp.addCookie(cookie);

        return new TokenResponse(access);
    }

    public void logout(Long userPk, HttpServletResponse resp) {
        refreshRepo.deleteByUserPk(userPk);

        Cookie cookie = new Cookie("refresh_token", "");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/auth");
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
    }

    private String sha256(String raw) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] dig = md.digest(raw.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(dig);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public AuthService(CompanyRepository companyRepository, UserRepository userRepository, RefreshTokenRepository refreshRepo, PasswordEncoder encoder, JwtService jwtService) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.refreshRepo = refreshRepo;
        this.encoder = encoder;
        this.jwtService = jwtService;
    }
}
