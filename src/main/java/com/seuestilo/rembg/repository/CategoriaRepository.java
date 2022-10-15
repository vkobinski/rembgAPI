package com.seuestilo.rembg.repository;

import com.seuestilo.rembg.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}