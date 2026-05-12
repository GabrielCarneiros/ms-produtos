package com.carneiro.ms_produtos.controller;

import com.carneiro.ms_produtos.business.dto.CategoriaRequestDTO;
import com.carneiro.ms_produtos.business.dto.out.CategoriaResponseDTO;
import com.carneiro.ms_produtos.business.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/categorias")
@Tag(name = "categorias", description = "Categorias de produtos")
public class CategoriaController {
    private final CategoriaService categoriaService;

    @PostMapping
    @Operation(summary = "Criar Categoria", description = "Cadastra uma nova categoria")
    @ApiResponse(responseCode = "200", description = "Categorias cadastrada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    public ResponseEntity<CategoriaResponseDTO> criarCategoria(@RequestBody @Valid CategoriaRequestDTO dto){
        return ResponseEntity.ok(categoriaService.criarCategoria(dto));
    }
    @GetMapping
    @Operation(summary = "Listar categorias", description = "Retorna lista de categorias")
    @ApiResponse(responseCode = "200", description = "Categorias listadas com sucesso")
    public List<CategoriaResponseDTO> listarCategorias(){
        return categoriaService.listarCategorias();
    }
    @GetMapping("/{id}")
    @Operation(summary = "Buscar categorias por id", description = "Retorna uma determinada categoria")
    @ApiResponse(responseCode = "200", description = "Categoria encontrada")
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    public ResponseEntity<CategoriaResponseDTO> buscarCategoruaPorId(@PathVariable Long id){
        return ResponseEntity.ok(categoriaService.buscarCategoriaPorId(id));
    }
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Categoria", description = "Atualiza por id uma categoria especifico")
    @ApiResponse(responseCode = "200", description = "Categoria atualizada")
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    @ApiResponse(responseCode = "400", description = "Dados invalidos")
    public ResponseEntity<CategoriaResponseDTO> atualizarCategoria(@PathVariable Long id, @RequestBody @Valid CategoriaRequestDTO dto){
        return ResponseEntity.ok(categoriaService.atualizarCategoria(id, dto));
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Categoria", description = "Deleta uma categoria específica")
    @ApiResponse(responseCode = "200", description = "Categoria deletada com sucesso")
    @ApiResponse(responseCode = "400", description = "Categoria não encontrada")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id){
        categoriaService.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
