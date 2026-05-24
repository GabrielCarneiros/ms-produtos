package com.carneiro.ms_produtos.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponseDTO {

    @Schema(
            description = "Token JWT para autenticação",
            example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    )
    private String token;

    @Schema(description = "Tipo de token", example = "Bearer")
    private String type = "Bearer";

    @Schema(description = "Tempo de expiração em minutos", example = "60")
    private Long expiresIn = 3600L;

    // Construtor com apenas token (para compatibilidade)
    public AuthResponseDTO(String token) {
        this.token = token;
        this.type = "Bearer";
        this.expiresIn = 3600L; // 1 hora
    }
}
