package com.devslopsleon.orders.services.auth;


import com.devslopsleon.orders.core.repository.CompanyRepository;
import com.devslopsleon.orders.core.repository.RefreshTokenRepository;
import com.devslopsleon.orders.core.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshRepo;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;


    public AuthenticationService(CompanyRepository companyRepository, UserRepository userRepository, RefreshTokenRepository refreshRepo, PasswordEncoder encoder, JwtService jwtService) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.refreshRepo = refreshRepo;
        this.encoder = encoder;
        this.jwtService = jwtService;
    }
}
