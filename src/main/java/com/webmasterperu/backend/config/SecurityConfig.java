package com.webmasterperu.backend.config;

import com.webmasterperu.backend.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationProvider;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        return http
                .csrf(csrf ->
                        csrf
                                .disable())
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers(HttpMethod.GET).permitAll()
                                .requestMatchers(HttpMethod.PUT).permitAll()
                                .requestMatchers(HttpMethod.POST).permitAll()
                                .requestMatchers(HttpMethod.DELETE).permitAll()

                                .requestMatchers(HttpMethod.OPTIONS).permitAll()
                                .requestMatchers("/auth/**").permitAll()
                                //.requestMatchers(HttpMethod.GET, "/auth/**").permitAll() // Permitir autenticación pública
                                //.requestMatchers(HttpMethod.GET, "/api/v1/user/**").authenticated()
                                //.requestMatchers(HttpMethod.GET, "/api/v1/hijos/**").authenticated()// Protección para acceso a datos del usuario
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionManager->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();


    }

}
