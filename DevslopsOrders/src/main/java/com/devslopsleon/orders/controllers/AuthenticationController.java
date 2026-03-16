package com.devslopsleon.orders.controllers;


import com.devslopsleon.orders.core.dto.LoginRequest;
import com.devslopsleon.orders.core.dto.TokenResponse;
import com.devslopsleon.orders.services.auth.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthService authService;

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest req, HttpServletResponse resp) {
        return authService.login(req, resp);
    }

    @PostMapping("/refresh")
    public TokenResponse refresh(@CookieValue(name="refresh_token", required=false) String refresh,
                                 HttpServletResponse resp) {
        return authService.refresh(refresh, resp);
    }

    @PostMapping("/logout")
    public void logout(Authentication auth, HttpServletResponse resp) {
        Long userPk = Long.valueOf(auth.getName());
        authService.logout(userPk, resp);
    }

    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }


}
