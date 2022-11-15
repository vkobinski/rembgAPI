package com.seuestilo.rembg.service;

import com.seuestilo.rembg.model.Categoria;
import com.seuestilo.rembg.repository.CategoriaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;


    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public ResponseEntity<Categoria> criaCategoria(Categoria categoria) {

        return ResponseEntity.ok(categoria);
    }

    public ResponseEntity<List<Categoria>> getCategorias() {
        return ResponseEntity.ok(categoriaRepository.findAll());
    }
}
