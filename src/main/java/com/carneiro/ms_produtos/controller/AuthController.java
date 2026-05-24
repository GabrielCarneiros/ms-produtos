package com.carneiro.ms_produtos.controller;

import com.carneiro.ms_produtos.business.dto.AuthRequestDTO;
import com.carneiro.ms_produtos.business.dto.AuthResponseDTO;
import com.carneiro.ms_produtos.infrastructure.security.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Endpoints de autenticação")
public class AuthController {

    private final JwtService jwtService;

    @PostMapping("/generate-token")
    @Operation(
            summary = "Gera token JWT",
            description = "Gera um token JWT válido por 1 hora. " +
                    "⚠️ ENDPOINT TEMPORÁRIO APENAS PARA TESTES. " +
                    "Será removido quando a API de usuário for criada."
    )
    public ResponseEntity<AuthResponseDTO> generateToken(
            @RequestBody AuthRequestDTO request
    ) {
        String token = jwtService.generateToken(request.getUsername());
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }
}

