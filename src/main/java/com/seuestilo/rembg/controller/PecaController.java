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

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<List<Peca>> getPecaByUserId(@PathVariable(name = "id") Long userId) {
        return pecaService.getPecaByUsuario(userId);
    }

    @GetMapping(path = "/user/{id}/{categoria}")
    public ResponseEntity<List<Peca>> getPecaByUserIdAndCategoria(@PathVariable(name = "id") Long userId, @PathVariable(name = "categoria") Long categoriaId) {
        return pecaService.getPecasByUsuarioAndCategoriaTipo(userId, categoriaId);
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
