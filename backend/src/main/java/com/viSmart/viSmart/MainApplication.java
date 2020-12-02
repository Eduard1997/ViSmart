package com.viSmart.viSmart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@SpringBootApplication
@Controller
@EnableJpaRepositories("com.viSmart.viSmart.Repository")
public class MainApplication {

    @Autowired
    private UserController userController;

    public static void main(String... args) {
        SpringApplication.run(MainApplication.class, args);

    }
}