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

import java.util.Map;

@RestController
public class UserController {

    private final UserInventory repository;

    private final UserService userServiceDetails;

    public UserController(UserInventory repository, ApplicationContext context) {
        this.repository = repository;
        this.userServiceDetails = context.getBean(UserService.class);
    }

    @PostMapping(value="/user", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Account> getUser(@RequestBody Map<String, String> json) {
        try {
            User user = repository.findByEmail(json.get("email"));
            if (user.getPassword().equals(json.get("password"))) {
                UserDetails userDTD = userServiceDetails.loadUserByUsername(user.getUsername());
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDTD, null, userDTD.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return new ResponseEntity<Account>(user, HttpStatus.OK);
            }
        }
        catch(NullPointerException e)
        {
            if (json.containsKey("email"))
                throw new AccountNotFoundException(json.get("email"));
        }
        throw new AccountNotFoundException("nobody");
    }

    @PostMapping(value="/get-user-details", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Account> getUserDetails(@RequestBody Map<String, String> json) {
        try {
            Integer userId = Integer.parseInt(json.get("userId"));
            User user = repository.findById(userId);
            return new ResponseEntity<Account>(user, HttpStatus.OK);
        }
        catch(NullPointerException e)
        {
                throw new AccountNotFoundException(json.get("id"));
        }
    }
    @GetMapping(value="/test")
    @ResponseBody
    public int test() {
        return 1;
    }
}
