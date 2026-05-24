package com.carneiro.ms_produtos.controller;

import com.carneiro.ms_produtos.business.dto.ProdutoRequestDTO;
import com.carneiro.ms_produtos.business.dto.out.ProdutoResponseDTO;
import com.carneiro.ms_produtos.business.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/produtos")
@Tag(name = "produtos", description = "Cadastro de produtos")

public class ProdutoController {
    private final ProdutoService produtoService;

    @GetMapping
    @Operation(summary = "Listar produtos", description = "Retorna lista paginada de produtos")
    @ApiResponse(responseCode = "200", description = "Produtos listados com sucesso")
    public ResponseEntity<Page<ProdutoResponseDTO>> listarProdutos(Pageable pageable){
        return ResponseEntity.ok(produtoService.listarProdutos(pageable));
    }

    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Cadastrar produtos", description = "Cadastra um novo produto")
    @ApiResponse(responseCode = "201", description = "Produtos cadastrados com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados Invalidos")
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    public ResponseEntity<ProdutoResponseDTO> criarProduto(@RequestBody @Valid ProdutoRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.criarProduto(dto));
    }
    @GetMapping("/{id}")
    @Operation(summary = "Buscar Produto por id", description = "Retorna um produto específico")
    @ApiResponse(responseCode = "200", description = "Produto encontrado")
    @ApiResponse(responseCode = "400", description = "Produto não encontrado")
    public ResponseEntity<ProdutoResponseDTO> buscarProdutoPorId(@PathVariable Long id){
        return ResponseEntity.ok(produtoService.buscarProdutoPorId(id));
    }
    @PutMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Atualizar Produto", description = "Atualiza por id um produto especifico")
    @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Produto ou categoria não encontrados")
    @ApiResponse(responseCode = "400", description = "Dados invalidos")
    public ResponseEntity<ProdutoResponseDTO> atualizarProduto(@PathVariable Long id, @RequestBody @Valid ProdutoRequestDTO dto){
        return ResponseEntity.ok(produtoService.atualizarProduto(id, dto));
    }
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Deletar Produto", description = "Deleta um produto específico")
    @ApiResponse(responseCode = "200", description = "Produto excluido com sucesso")
    @ApiResponse(responseCode = "400", description = "Produto não encontrado")
    public ResponseEntity<Void> delatarProduto(@PathVariable Long id){
        produtoService.deletaProdutoPorId(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/categoria/{id}")
    @Operation(summary = "Buscar produtos por categoria",  description = "Retorna todos os produtos de uma categoria específica")
    @ApiResponse(responseCode = "200", description = "Produtos encontrados com sucesso")
    @ApiResponse(responseCode = "400", description = "Categoria não encontrada")
    public ResponseEntity<List<ProdutoResponseDTO>>buscarProdutosPorCategoria(@PathVariable Long id){
        return ResponseEntity.ok(produtoService.buscarProdutosPorCategoria(id));
    }
    @GetMapping("/busca")
    @Operation(summary = "Buscar produtos por nome",  description = "Retorna produtos que contenham o nome informado")
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    public ResponseEntity<List<ProdutoResponseDTO>> buscarProdutosPorNome(@RequestParam String nome){
        return ResponseEntity.ok(produtoService.buscarProdutosPorNome(nome));
    }

}
