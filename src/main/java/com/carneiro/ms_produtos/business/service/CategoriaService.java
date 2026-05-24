package com.carneiro.ms_produtos.business.service;

import com.carneiro.ms_produtos.business.converter.CategoriaConverter;
import com.carneiro.ms_produtos.business.dto.CategoriaRequestDTO;
import com.carneiro.ms_produtos.business.dto.out.CategoriaResponseDTO;
import com.carneiro.ms_produtos.infrastructure.entity.Categoria;
import com.carneiro.ms_produtos.infrastructure.exceptions.BusinessException;
import com.carneiro.ms_produtos.infrastructure.exceptions.ResourceNotFoundException;
import com.carneiro.ms_produtos.infrastructure.repository.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;
    private final CategoriaConverter categoriaConverter;

    public CategoriaResponseDTO criarCategoria(CategoriaRequestDTO dto){
        Categoria categoria = categoriaConverter.paraEnitty(dto);
        Categoria categoriaSalva = categoriaRepository.save(categoria);
        return categoriaConverter.paraResponseDTO(categoriaSalva);
    }

    public List<CategoriaResponseDTO> listarCategorias(){
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream().map(categoriaConverter::paraResponseDTO).toList();
    }

    public CategoriaResponseDTO buscarCategoriaPorId(Long id){
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Categoria não encontrada"));
        return categoriaConverter.paraResponseDTO(categoria);
    }

    public CategoriaResponseDTO atualizarCategoria(Long id, CategoriaRequestDTO dto){
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Categoria não encontrada"));
        categoria.setNome(dto.getNome());
        Categoria categoriaSalva = categoriaRepository.save(categoria);
        return categoriaConverter.paraResponseDTO(categoriaSalva);
    }

    public void deletarCategoria(Long id){
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Categoria não encontrada"));

        if (!categoria.getProdutos().isEmpty()){
            throw new BusinessException("Não é possível deletar categoria com produtos vinculados");
        }

        categoriaRepository.delete(categoria);
    }
}
