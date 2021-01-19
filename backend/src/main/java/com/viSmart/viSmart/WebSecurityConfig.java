package com.viSmart.viSmart;


import com.viSmart.viSmart.Repository.UserInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import org.springframework.web.socket.server.standard.ServerEndpointRegistration;

import javax.servlet.ServletContext;
import javax.websocket.DeploymentException;
import javax.websocket.Endpoint;
import javax.websocket.WebSocketContainer;
import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
@ConditionalOnWebApplication
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserInventory userInventory;
    private WebRtcSignalingEndpoint endpoint;

    public WebSecurityConfig(UserInventory userInventory){
       this.userInventory = userInventory;
       this.endpoint = new WebRtcSignalingEndpoint();
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login")
                .permitAll()
                .antMatchers("/signal/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable()
                .addFilter(new UserAuthServiceFilter(authenticationManager()))
                .addFilter(new UserServiceFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Deprecated
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsServiceCustom(userInventory)).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return authenticationManager();
    }

    private ServletContext servletContext;

    @Autowired
    public void setServletContext(ServletContext servletContext)
    {
        this.servletContext = servletContext;
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        ServerEndpointExporter serverEndpointExporter = new ServerEndpointExporter();
        ServerContainer serverContainer = (ServerContainer) servletContext.getAttribute("javax.websocket.server.ServerContainer");
        try {
            CustomSpringConfigurator configurator = getApplicationContext().getBean(CustomSpringConfigurator.class);
            ServerEndpointConfig config = ServerEndpointConfig.Builder.create(WebRtcSignalingEndpoint.class,"/signal/{classname}/{token}")
                    .configurator(configurator).build();
            serverContainer.addEndpoint(config);
        } catch (DeploymentException e) {
            e.printStackTrace();
        }
        serverEndpointExporter.setServerContainer(serverContainer);
        serverEndpointExporter.setApplicationContext(this.getApplicationContext());
        serverEndpointExporter.afterPropertiesSet();
        return serverEndpointExporter;
    }

    private static final Map<String,VideoRest> videoConferences = Collections.synchronizedMap(new HashMap<String,VideoRest>());

    @Bean
    public WebRtcSignalingEndpoint webRtcSignalingEndpoint()  {
        return new WebRtcSignalingEndpoint();
    }

    @Bean
    public Map<String,VideoRest> getVideoConferences(){
        return videoConferences;
    }
}
