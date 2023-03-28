package com.seuestilo.rembg.controller;

import com.seuestilo.rembg.model.Marca;
import com.seuestilo.rembg.model.Tamanho;
import com.seuestilo.rembg.service.MarcaService;
import com.seuestilo.rembg.service.TamanhoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/marca")
@Slf4j
public class MarcaController {

    private final MarcaService marcaService;

    @Autowired
    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @GetMapping
    public ResponseEntity<List<Marca>> getMarcas() {
        return marcaService.getMarcas();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Marca> getMarcaById(@PathVariable Long id) {
        return marcaService.findMarcaById(id);
    }

    @PostMapping
    public ResponseEntity<Marca> criaMarca(@RequestBody Marca marca) {
        return marcaService.criaMarca(marca);
    }

}