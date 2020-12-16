package com.viSmart.viSmart;

import com.viSmart.viSmart.Repository.UserInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceCustom implements UserDetailsService {

    UserInventory userInventory;

    public UserDetailsServiceCustom(UserInventory userInventory){
        this.userInventory = userInventory;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws AccountNotFoundException {
        User user = (User) userInventory.findByEmail(email);
        if (user == null) {
            throw new AccountNotFoundException(email);
        }
        return new
                org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                new ArrayList<>());
    }
}
