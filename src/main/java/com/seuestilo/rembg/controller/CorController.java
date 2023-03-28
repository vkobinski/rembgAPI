package com.seuestilo.rembg.controller;

import com.seuestilo.rembg.model.Categoria;
import com.seuestilo.rembg.model.Cor;
import com.seuestilo.rembg.model.Tamanho;
import com.seuestilo.rembg.service.CategoriaService;
import com.seuestilo.rembg.service.CorService;
import com.seuestilo.rembg.service.TamanhoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cor")
@Slf4j
public class CorController {

    private final CorService corService;

    @Autowired
    public CorController(CorService corService) {
        this.corService = corService;
    }

    @GetMapping
    public ResponseEntity<List<Cor>> getCores() {
        return corService.getCores();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Cor> getCoresById(@PathVariable Long id) {
        return corService.findCorById(id);
    }

    @PostMapping
    public ResponseEntity<Cor> createCor(@RequestBody Cor cor) {
        return corService.criaCor(cor);
    }

}