package com.apis.security;
/*
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        // .requestMatchers("/products").hasAnyAuthority("ADMIN").anyRequest().permitAll() // for pattern matching
                        .anyRequest().authenticated() // to make all apis unauthorized by default
                        //.anyRequest().permitAll() // no authorization
                )
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));
        return http.build();
    }
}*/
