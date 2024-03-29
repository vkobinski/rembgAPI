package com.seuestilo.rembg.controller;

import com.seuestilo.rembg.model.Peca;
import com.seuestilo.rembg.service.PecaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/peca")
@Slf4j
public class PecaController {

    private final PecaService pecaService;

    @Autowired
    public PecaController(PecaService pecaService) {
        this.pecaService = pecaService;
    }

    @GetMapping
    public ResponseEntity<List<Peca>> getPecas() {
        return pecaService.getPecas();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Peca> getPecaById(@PathVariable Long id) {
        return pecaService.getPecaById(id);
    }

    @PostMapping
    public ResponseEntity<Peca> createPeca(@RequestBody Peca peca) {
        return pecaService.createPeca(peca);
    }

    @DeleteMapping
    public ResponseEntity<Object> deletePecaById(@PathVariable Long id) {
        return pecaService.deleteById(id);
    }
}
