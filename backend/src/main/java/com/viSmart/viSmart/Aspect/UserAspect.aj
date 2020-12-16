package com.viSmart.viSmart.Aspect;

import com.viSmart.viSmart.Repository.UserInventory;
import com.viSmart.viSmart.User;

public aspect UserAspect {
    pointcut saveUser(UserInventory userRepository, User user) : call(userRepository.save(User user));

    before(UserInventory userRepository, User user): trace(){
        System.out.println("The user "+user.getEmail()+" is going to be created");
    }

    after(UserInventory userRepository, User user): trace(){
        if (userRepository.existsByEmailAndPassword(user.getEmail(), user.getPassword()))
            System.out.println("The user "+user.getEmail()+" is successfully saved in DB and it has own password.");
    }
}
