package com.devslopsleon.orders.services.helpers;


import com.devslopsleon.orders.core.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserStockLineHelper {

    private final UserRepository  userRepository;


    public boolean existUserRegistered(final String mail, final Long companyId){
        return true;
    }

    public UserStockLineHelper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
