package com.viSmart.viSmart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${management.port}")
    private int managementPort;

    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and()
                .authorizeRequests()
                .antMatchers(String.valueOf(forPortAndPath(managementPort,"/")))
                .permitAll()
                .anyRequest()
                .authenticated()
                .requestMatchers(forPortAndPath(managementPort,"/login"))
                .permitAll()
                .requestMatchers(forPortAndPath(managementPort,"/logout"))
                .permitAll();
    }

    private RequestMatcher forPortAndPath(final int port, @NonNull final String pathPattern) {
        return new AndRequestMatcher(forPort(port), new AntPathRequestMatcher(pathPattern));
    }

    private RequestMatcher forPort(final int port) {
        return (HttpServletRequest request) -> { return port == request.getLocalPort(); };
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new UserService();
    }
}
