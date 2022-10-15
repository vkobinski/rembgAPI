package com.seuestilo.rembg.service;

import com.seuestilo.rembg.model.Peca;
import com.seuestilo.rembg.repository.PecaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PecaService {

    private final PecaRepository pecaRepository;

    @Autowired
    public PecaService(PecaRepository pecaRepository) {
        this.pecaRepository = pecaRepository;
    }

    public ResponseEntity<List<Peca>> getPecas() {
        return ResponseEntity.ok(pecaRepository.findAll());
    }

    public ResponseEntity<Peca> createPeca(Peca peca) {
        try {
           return ResponseEntity.ok(pecaRepository.save(peca));
        } catch (Exception e) {
           log.error(e.getMessage());
           return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<Peca> getPecaById(Long id) {
        try {
           return pecaRepository.findById(id)
                   .map((peca) -> ResponseEntity.ok().body(peca))
                   .orElseThrow(() -> {
                       throw new IllegalStateException("Não foi encontrada Peça com ID " + id);
                   });
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Object> deleteById(Long id) {
        log.info("Deletando Peça com ID: " + id);

        // TODO: criar services que têm relação

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Peca> updatePecaById() {

        // TODO

        return ResponseEntity.ok().build();
    }
}
