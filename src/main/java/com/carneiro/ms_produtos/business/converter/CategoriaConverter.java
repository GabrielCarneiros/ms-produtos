package com.carneiro.ms_produtos.business.converter;

import com.carneiro.ms_produtos.business.dto.CategoriaRequestDTO;
import com.carneiro.ms_produtos.business.dto.out.CategoriaResponseDTO;
import com.carneiro.ms_produtos.infrastructure.entity.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaConverter {
    public Categoria paraEnitty(CategoriaRequestDTO dto){
        return Categoria.builder()
                .nome(dto.getNome())

                .build();
    }

    public CategoriaResponseDTO paraResponseDTO(Categoria categoria){
        return CategoriaResponseDTO.builder()
                .id(categoria.getId())
                .nome(categoria.getNome())
                .build();
    }
}
