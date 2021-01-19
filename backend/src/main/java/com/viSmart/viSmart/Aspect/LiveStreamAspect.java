package com.viSmart.viSmart.Aspect;

import com.viSmart.viSmart.User;
import com.viSmart.viSmart.UserAuthServiceFilter;
import com.viSmart.viSmart.WebRtcSignalingEndpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.viSmart.viSmart.Repository.UserInventory;

import javax.websocket.Session;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Aspect
@Configuration
public class LiveStreamAspect {

    private UserInventory userInventory;

    private UserAuthServiceFilter userAuthServiceFilter;

    @Autowired
    public LiveStreamAspect(UserInventory userInventory) {
        this.userInventory = userInventory;
    }

    @Autowired
    public void setUserAuthServiceFilter(UserAuthServiceFilter userAuthServiceFilter){
        this.userAuthServiceFilter = userAuthServiceFilter;
    }

    @After("execution(* *.whenOpening(..))")
    void signalOpening(JoinPoint jp ){
        MethodSignature methodSig = (MethodSignature) jp.getSignature();
        Object[] params = jp.getArgs();
        List<String> parametersName = Arrays.asList(methodSig.getParameterNames());
        String token = (String) params[parametersName.indexOf("token")];
        String classname = (String) params[parametersName.indexOf("classname")];

        Authentication authentication = userAuthServiceFilter.getAuthentication(token);
        String username = (String)  authentication.getPrincipal();
        User user = userInventory.findByEmail(username);
        String role;
        switch (user.getRole()){
            case 1: role="admin"; break;
            case 2: role="student";break;
            case 3: role="teacher";break;
            default: role="unknown";
        }
        System.out.println("Open for user "+user.getEmail()+" which has "+ role+" in "+classname+" class");

    }

    @Before("execution(* *.process(..)) && args(data,..)")
    void signalProcess(String data){
        System.out.println("Got signal - " + data);
    }

     @After("execution(* *.whenClosing(..))" )
     void signalClosing(JoinPoint jp) {
         MethodSignature methodSig = (MethodSignature) jp.getSignature();
         Object[] params = jp.getArgs();
         List<String> parametersName = Arrays.asList(methodSig.getParameterNames());
         String token = (String) params[parametersName.indexOf("token")];

         Authentication authentication = userAuthServiceFilter.getAuthentication(token);
         String username = (String)  authentication.getPrincipal();
         User user = userInventory.findByEmail(username);
         System.out.println("Close the session user !"+user.getEmail());
     }
}
