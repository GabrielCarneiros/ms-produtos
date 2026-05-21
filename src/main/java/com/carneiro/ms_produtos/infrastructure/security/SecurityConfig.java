package com.carneiro.ms_produtos.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
         throws Exception {

         return httpSecurity
                 .csrf(AbstractHttpConfigurer::disable)

                 // 📌 Novo: Configurar para stateless (sem sessão)
                 .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                 .authorizeHttpRequests(auth -> auth

                         .requestMatchers(
                                 "/auth/**",
                                 "/swagger-ui/**",
                                 "/v3/api-docs/**"
                         ).permitAll()

                         .requestMatchers(
                                 HttpMethod.GET,
                                 "/produtos/**"
                         ).permitAll()

                         .requestMatchers(
                                 HttpMethod.GET,
                                 "/categorias/**"
                         ).permitAll()

                         .anyRequest().authenticated()
                 )

                 // 📌 Novo: Adicionar o filtro JWT ANTES do UsernamePasswordAuthenticationFilter
                 .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

                 .build();
         }
     }

