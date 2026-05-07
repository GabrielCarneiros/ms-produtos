package com.carneiro.ms_produtos.business.dto;

import com.carneiro.ms_produtos.infrastructure.entity.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRequestDTO {

    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    @NotNull
    private BigDecimal preco;
    @NotNull
    private Integer quantidade;

    private Long categoriaId;
}
