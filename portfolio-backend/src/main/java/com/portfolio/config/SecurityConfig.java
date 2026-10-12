package com.portfolio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                // 1. Allow public access to your static HTML/CSS/JS frontend files
                .requestMatchers("/", "/index.html", "/more-about-me.html", "/style.css", "/app.js", "/assets/**").permitAll()
                
                // 2. ALLOW PUBLIC ACCESS TO YOUR API ENDPOINTS (This fixes your 403 error!)
                .requestMatchers("/api/**").permitAll() 
                
                // 3. Require authentication for anything else
                .anyRequest().authenticated()
            )
            .csrf(csrf -> csrf.disable()); // Disable CSRF for simple REST APIs

        return http.build();
    }
}