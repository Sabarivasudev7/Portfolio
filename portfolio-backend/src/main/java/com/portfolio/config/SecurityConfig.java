package com.portfolio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disables CSRF so POST/PUT requests work in Swagger
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // Allows access to all API endpoints & Swagger without login
            );
        return http.build();
    }
}