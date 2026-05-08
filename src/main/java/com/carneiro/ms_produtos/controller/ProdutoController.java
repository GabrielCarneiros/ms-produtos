package com.carneiro.ms_produtos.controller;

import com.carneiro.ms_produtos.business.dto.ProdutoRequestDTO;
import com.carneiro.ms_produtos.business.dto.out.ProdutoResponseDTO;
import com.carneiro.ms_produtos.business.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/produtos")

public class ProdutoController {
    private final ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<Page<ProdutoResponseDTO>> listarProdutos(Pageable pageable){
        return ResponseEntity.ok(produtoService.listarProdutos(pageable));
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> criarProduto(@RequestBody @Valid ProdutoRequestDTO dto){
        return ResponseEntity.ok(produtoService.criarProduto(dto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarProdutoPorId(@PathVariable Long id){
        return ResponseEntity.ok(produtoService.buscarProdutoPorId(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizarProduto(@PathVariable Long id, @RequestBody @Valid ProdutoRequestDTO dto){
        return ResponseEntity.ok(produtoService.atualizarProduto(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delatarProduto(@PathVariable Long id){
        produtoService.deletaProdutoPorId(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<ProdutoResponseDTO>>buscarProdutosPorCategoria(@PathVariable Long id){
        return ResponseEntity.ok(produtoService.buscarProdutosPorCategoria(id));
    }
    @GetMapping("/busca")
    public ResponseEntity<List<ProdutoResponseDTO>> buscarProdutosPorNome(@RequestParam String nome){
        return ResponseEntity.ok(produtoService.buscarProdutosPorNome(nome));
    }

}
