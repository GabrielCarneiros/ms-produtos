package com.carneiro.ms_produtos.infrastructure.repository;

import com.carneiro.ms_produtos.infrastructure.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
