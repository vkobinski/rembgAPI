package com.seuestilo.rembg.controller;

import com.seuestilo.rembg.model.Categoria;
import com.seuestilo.rembg.model.Categoria;
import com.seuestilo.rembg.service.CategoriaService;
import com.seuestilo.rembg.service.CategoriaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categoria")
@Slf4j
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> getCategorias() {
        return categoriaService.getCategorias();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Long id) {
        return categoriaService.findCategoriaById(id);
    }

    @PostMapping
    public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria categoria) {
        return categoriaService.criaCategoria(categoria);
    }

}