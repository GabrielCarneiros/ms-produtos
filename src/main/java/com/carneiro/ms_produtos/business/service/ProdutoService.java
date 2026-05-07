package com.carneiro.ms_produtos.business.service;

import com.carneiro.ms_produtos.business.converter.ProdutoConverter;
import com.carneiro.ms_produtos.business.dto.ProdutoRequestDTO;
import com.carneiro.ms_produtos.business.dto.out.ProdutoResponseDTO;
import com.carneiro.ms_produtos.infrastructure.entity.Produto;
import com.carneiro.ms_produtos.infrastructure.exceptions.ResourceNotFoundException;
import com.carneiro.ms_produtos.infrastructure.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final ProdutoConverter produtoConverter;

    public List<Produto> listarProdutos(){
        return produtoRepository.findAll();
    }

    public ProdutoResponseDTO criarProduto(ProdutoRequestDTO dto){
        Produto produto = produtoConverter.paraEntity(dto);
        Produto produtoSalvo = produtoRepository.save(produto);
        return produtoConverter.paraResponseDTO(produtoSalvo);
    }
    public ProdutoResponseDTO buscarProdutoPorId(Long id){
        Produto produto = produtoRepository
                .findById(id).orElseThrow(() ->
                        new ResourceNotFoundException("Produto não encontrado"));
        return produtoConverter.paraResponseDTO(produto);
    }
    public ProdutoResponseDTO atualizarProduto(Long id, ProdutoRequestDTO dto){
       Produto produto = produtoRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Produto não encontrado"));
            produto.setNome(dto.getNome());
            produto.setDescricao(dto.getDescricao());
            produto.setPreco(dto.getPreco());
            produto.setQuantidade(dto.getQuantidade());

            Produto produtoAtualizado = produtoRepository.save(produto);
            return produtoConverter.paraResponseDTO(produtoAtualizado);
    }

}
