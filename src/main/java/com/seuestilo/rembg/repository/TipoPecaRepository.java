package com.seuestilo.rembg.repository;

import com.seuestilo.rembg.model.Categoria;
import com.seuestilo.rembg.model.TipoPeca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoPecaRepository extends JpaRepository<TipoPeca, Long> {

    Optional<TipoPeca> getTipoPecasByCategoria(Categoria categoriaId);
}