package com.carneiro.ms_produtos.controller;

import com.carneiro.ms_produtos.business.dto.ProdutoRequestDTO;
import com.carneiro.ms_produtos.business.dto.out.ProdutoResponseDTO;
import com.carneiro.ms_produtos.business.service.ProdutoService;
import com.carneiro.ms_produtos.infrastructure.entity.Produto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/produtos")

public class ProdutoController {
    private final ProdutoService produtoService;

    @GetMapping
    public List<Produto> listarProdutos(){
        return produtoService.listarProdutos();
    }

    @PostMapping
    public ProdutoResponseDTO criarProduto(@RequestBody @Valid ProdutoRequestDTO dto){
        return produtoService.criarProduto(dto);
    }
    @GetMapping("/{id}")
    public ProdutoResponseDTO buscarProdutoPorId(@PathVariable Long id){
        return produtoService.buscarProdutoPorId(id);
    }
    @PutMapping("/{id}")
    public ProdutoResponseDTO atualizarProduto(@PathVariable Long id, @RequestBody @Valid ProdutoRequestDTO dto){
        return produtoService.atualizarProduto(id, dto);
    }
    @DeleteMapping("/{id}")
    public void delatarProduto(@PathVariable Long id){
        produtoService.deletaProdutoPorId(id);
    }

}
