package com.carneiro.ms_produtos.business.dto.out;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutoResponseDTO {

    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    private Integer quantidade;
}
