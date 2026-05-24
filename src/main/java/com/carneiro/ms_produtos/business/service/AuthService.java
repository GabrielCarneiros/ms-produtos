package com.carneiro.ms_produtos.business.service;

import com.carneiro.ms_produtos.business.dto.AuthRequestDTO;
import com.carneiro.ms_produtos.infrastructure.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;

    public String authenticate(AuthRequestDTO request) {

        // Por enquanto, apenas gera token com o username
        return jwtService.generateToken(request.getUsername());
    }
}

