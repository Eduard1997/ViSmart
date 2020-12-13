package com.viSmart.viSmart;

import com.viSmart.viSmart.Repository.UserInventory;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
public class UserController {

    private final UserInventory repository;

    public UserController(UserInventory repository) {
        this.repository = repository;
    }

    @GetMapping(value="/get-user-details", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> getUser() {
        try {
            Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = repository.findByEmail(principal.getName());
            return new ResponseEntity<Account>(user, HttpStatus.OK);
        }
        catch(NullPointerException e)
        {
            throw new AccountNotFoundException("nobody");
        }
    }

}
