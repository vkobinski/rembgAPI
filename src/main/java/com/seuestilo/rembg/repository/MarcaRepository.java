package com.seuestilo.rembg.repository;

import com.seuestilo.rembg.model.Marca;
import com.seuestilo.rembg.model.Tamanho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
}