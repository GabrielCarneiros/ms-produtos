package com.carneiro.ms_produtos.business.dto;

import com.carneiro.ms_produtos.infrastructure.entity.Produto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaRequestDTO {

    private String nome;

}
