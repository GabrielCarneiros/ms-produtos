package com.carneiro.ms_produtos.controller;

import com.carneiro.ms_produtos.business.dto.CategoriaRequestDTO;
import com.carneiro.ms_produtos.business.dto.out.CategoriaResponseDTO;
import com.carneiro.ms_produtos.business.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> criarCategoria(@RequestBody @Valid CategoriaRequestDTO dto){
        return ResponseEntity.ok(categoriaService.criarCategoria(dto));
    }
    @GetMapping
    public List<CategoriaResponseDTO> listarCategorias(){
        return categoriaService.listarCategorias();
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> buscarCategoruaPorId(@PathVariable Long id){
        return ResponseEntity.ok(categoriaService.buscarCategoriaPorId(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> atualizarCategoria(@PathVariable Long id, @RequestBody @Valid CategoriaRequestDTO dto){
        return ResponseEntity.ok(categoriaService.atualizarCategoria(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id){
        categoriaService.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
