package com.carneiro.ms_produtos.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequestDTO {

    @Schema(description = "Username ou email do usuário", example = "joao@email.com")
    private String username;

    @Schema(description = "Senha do usuário", example = "senha123")
    private String password;
}