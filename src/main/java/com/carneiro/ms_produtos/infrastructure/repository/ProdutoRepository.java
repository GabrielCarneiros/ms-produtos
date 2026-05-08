package com.carneiro.ms_produtos.infrastructure.repository;


import com.carneiro.ms_produtos.infrastructure.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByNomeContainingIgnoreCase(String nome);
    List<Produto> findByCategoriaId(Long id);
}
