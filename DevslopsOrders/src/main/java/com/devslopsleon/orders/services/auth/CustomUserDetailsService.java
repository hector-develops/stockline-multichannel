package com.devslopsleon.orders.services.auth;


import com.devslopsleon.orders.core.models.person.User;
import com.devslopsleon.orders.core.repository.UserRepository;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService, StockLineCustomUserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userIdPkAsString) throws UsernameNotFoundException {
        Long userPk = Long.valueOf(userIdPkAsString);
        //User u = userRepository.findActiveByCompanyIdAndMailWithRole();
        User u = userRepository.findById(userPk)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // si tienes wStatus (activo)
        if (!u.iswStatus()) throw new DisabledException("User disabled");
        return org.springframework.security.core.userdetails.User
                .withUsername(String.valueOf(u.getId()))
                .password(u.getPassword())
                .authorities(new SimpleGrantedAuthority("ROLE_" + u.getRole().getCode()))
                .build();
    }

    public UserDetails loadUserByUsernameMailAndCompany(String userIdPkAsString, Long companyId, String mail) throws UsernameNotFoundException {
        Long userPk = Long.valueOf(userIdPkAsString);
        User u = userRepository.findActiveByCompanyIdAndMailWithRole(mail, companyId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        //User u = userRepository.findById(userPk)
         //       .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // si tienes wStatus (activo)
        if (!u.iswStatus()) throw new DisabledException("User disabled");
        return org.springframework.security.core.userdetails.User
                .withUsername(String.valueOf(u.getId()))
                .password(u.getPassword())
                .authorities(new SimpleGrantedAuthority("ROLE_" + u.getRole().getCode()))
                .build();
    }
}
