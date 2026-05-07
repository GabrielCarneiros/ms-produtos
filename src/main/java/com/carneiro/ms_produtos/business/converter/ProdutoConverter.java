package com.carneiro.ms_produtos.business.converter;

import com.carneiro.ms_produtos.business.dto.ProdutoRequestDTO;
import com.carneiro.ms_produtos.business.dto.out.ProdutoResponseDTO;
import com.carneiro.ms_produtos.infrastructure.entity.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoConverter {
    public Produto paraEntity(ProdutoRequestDTO dto){
        return Produto.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .preco(dto.getPreco())
                .quantidade(dto.getQuantidade())
                .build();
    }

    //----------------------------------------------------------------------

    public ProdutoResponseDTO paraResponseDTO(Produto produto){
        return ProdutoResponseDTO.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .preco(produto.getPreco())
                .quantidade(produto.getQuantidade())
                .build();
    }


}
