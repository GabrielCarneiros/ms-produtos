package com.carneiro.ms_produtos.infrastructure.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,

            @NonNull HttpServletResponse response,

            @NonNull FilterChain filterChain

    ) throws ServletException, IOException {

        // PASSO 1:
        // Pega o header Authorization da request
        String authHeader =
                request.getHeader("Authorization");

        // PASSO 2:
        // Verifica se existe token e se começa com Bearer
        if(authHeader == null
                || !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);

            return;
        }

        // PASSO 3:
        // Remove "Bearer " do token
        String token =
                authHeader.substring("Bearer ".length());

        // PASSO 4:
        // Verifica se token é válido
        if(!jwtService.isTokenValid(token)) {

            filterChain.doFilter(request, response);

            return;
        }

        // PASSO 5:
        // Extrai username do token
        String username =
                jwtService.extractUsername(token);

        // PASSO 6:
        // Verifica se usuário ainda não foi autenticado
        if(SecurityContextHolder.getContext()
                .getAuthentication() == null) {

            // Cria autenticação do Spring Security
            UsernamePasswordAuthenticationToken authToken =

                    new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            List.of()
                    );

            // Define usuário autenticado no contexto
            SecurityContextHolder.getContext()
                    .setAuthentication(authToken);
        }

        // PASSO 7:
        // Continua a request
        filterChain.doFilter(request, response);
    }
}