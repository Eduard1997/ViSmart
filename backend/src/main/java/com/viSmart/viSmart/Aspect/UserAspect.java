package com.viSmart.viSmart.Aspect;

import com.viSmart.viSmart.Repository.UserInventory;
import com.viSmart.viSmart.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;

@Aspect
@Component
public class UserAspect {

    @Before("execution(* *.save(..)) && args(user)")
    void printUserCreationMessageBefore(JoinPoint jp, User user){
        if (((UserInventory) jp.getThis()).existsByEmailAndPassword(user.getEmail(), user.getPassword()))
            System.out.println("The user"+user.getEmail()+" exists");
        else
            System.out.println("The user "+user.getEmail()+" is going to be created");
    }

    @After("execution(* *.save(..)) && args(user)")
    void printUserCreationMessageAfter(JoinPoint jp,User user) {
        if (((UserInventory) jp.getThis()).existsByEmailAndPassword(user.getEmail(), user.getPassword()))
            System.out.println("The user "+user.getEmail()+" is successfully saved in DB and it has own password.");
    }
}
