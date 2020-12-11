package com.viSmart.viSmart;

import com.viSmart.viSmart.Repository.UserInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserInventory userInventory;

    @Override
    public UserDetails loadUserByUsername(String username) throws AccountNotFoundException {
        User user = (User) userInventory.findByUsername(username);
        if (user == null) {
            throw new AccountNotFoundException(username);
        }
        GrantedAuthority role = new GrantedAuthority() {
            Integer role;

            @Override
            public String getAuthority() {
                return role.toString();
            }
        };
        ArrayList<GrantedAuthority> listRoles = new ArrayList<>();
        listRoles.add(role);
        return new
                org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                true,
                true,
                true,
                true,
                listRoles);
    }
}
