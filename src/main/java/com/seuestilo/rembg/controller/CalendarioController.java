package com.seuestilo.rembg.controller;

import com.seuestilo.rembg.model.Calendario;
import com.seuestilo.rembg.model.Categoria;
import com.seuestilo.rembg.service.CalendarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/calendario")
@Slf4j
public class CalendarioController {

    private final CalendarioService calendarioService;

    @Autowired
    public CalendarioController(CalendarioService calendarioService) {
        this.calendarioService = calendarioService;
    }


    @GetMapping
    public ResponseEntity<List<Calendario>> getCalendarios() {
        return calendarioService.getCalendarios();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Calendario> getCalendarioById(@PathVariable Long id) {
        return calendarioService.findCalendarioById(id);
    }

    @PostMapping
    public ResponseEntity<Calendario> createCalendario(@RequestBody Calendario calendario) {
        return calendarioService.criaCalendario(calendario);
    }

    @PostMapping("/usuario/{id}")
    public ResponseEntity<Calendario> createCalendarioByUserId(@PathVariable Long id, @RequestBody Calendario calendario) {
        Optional<Calendario> calendarioByUserId = calendarioService.createCalendarioByUserId(id, calendario);
        return calendarioByUserId.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping(path = "/usuario/{id}")
    public ResponseEntity<List<Calendario>> findCalendariosByUser(@PathVariable Long id) {
       return ResponseEntity.ok(calendarioService.findCalendariosByUserId(id));
    }

}