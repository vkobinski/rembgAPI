package com.seuestilo.rembg.controller;

import com.seuestilo.rembg.model.Look;
import com.seuestilo.rembg.model.Peca;
import com.seuestilo.rembg.service.LookService;
import com.seuestilo.rembg.service.PecaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/look")
@Slf4j
public class LookController {

    private final LookService lookService;

    @Autowired
    public LookController(LookService lookService) {
        this.lookService = lookService;
    }

    @GetMapping
    public ResponseEntity<List<Look>> getLooks() {
        return lookService.getLooks();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Look> getPecaById(@PathVariable Long id) {
        return lookService.findLookById(id);
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<List<Look>> getLookByUser(@PathVariable(name = "id") Long userId) {
        return lookService.getLookByUsuario(userId);
    }

    @PostMapping
    public ResponseEntity<Look> createLook(@RequestBody Look look) {
        return lookService.criaLook(look);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteLookById(@PathVariable Long id) {
        return lookService.deleteLook(id);
    }
}
