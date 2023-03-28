package com.seuestilo.rembg.controller;

import com.seuestilo.rembg.model.Categoria;
import com.seuestilo.rembg.model.Tamanho;
import com.seuestilo.rembg.service.CategoriaService;
import com.seuestilo.rembg.service.TamanhoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tamanho")
@Slf4j
public class TamanhoController {

    private final TamanhoService tamanhoService;

    @Autowired
    public TamanhoController(TamanhoService tamanhoService) {
        this.tamanhoService = tamanhoService;
    }

    @GetMapping
    public ResponseEntity<List<Tamanho>> getCategorias() {
        return tamanhoService.getTamanhos();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Tamanho> getTamanhoById(@PathVariable Long id) {
        return tamanhoService.findTamanhoById(id);
    }

    @PostMapping
    public ResponseEntity<Tamanho> createTamanho(@RequestBody Tamanho tamanho) {
        return tamanhoService.criaTamanho(tamanho);
    }

}