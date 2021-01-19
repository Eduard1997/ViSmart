package com.viSmart.viSmart;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.servlet.Filter;
import javax.servlet.ServletContext;

@Configuration
@SpringBootApplication
@Controller
@EnableWebSocket
@EnableJpaRepositories("com.viSmart.viSmart.Repository")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MainApplication {

    public static void main(String... args) {
        SpringApplication.run(MainApplication.class, args);

    }

}