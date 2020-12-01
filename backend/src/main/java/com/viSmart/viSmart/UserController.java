package com.viSmart.viSmart;

import com.viSmart.viSmart.Repository.UserInventory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {

    private final UserInventory repository;

    public UserController(UserInventory repository) {
        this.repository = repository;
    }

    @PostMapping(value="/user", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Account> getUser(@RequestBody Map<String, String> json) {
        try {
            User user = repository.findByEmail(json.get("email"));
            if (user.getPassword().equals(json.get("password")))
                return new ResponseEntity<Account>(user, HttpStatus.OK);
        }
        catch(NullPointerException e)
        {
            if (json.containsKey("email"))
                throw new AccountNotFoundException(json.get("email"));
        }
        throw new AccountNotFoundException("nobody");
    }

}
