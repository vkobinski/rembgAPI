package com.seuestilo.rembg.service;

import com.seuestilo.rembg.model.Peca;
import com.seuestilo.rembg.model.TipoPeca;
import com.seuestilo.rembg.repository.PecaRepository;
import com.seuestilo.rembg.repository.TipoPecaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PecaService {

    private final PecaRepository pecaRepository;
    private final LookService lookService;
    private final TipoPecaRepository tipoPecaRepository;

    @Autowired
    public PecaService(PecaRepository pecaRepository, LookService lookService, TipoPecaRepository tipoPecaRepository) {
        this.pecaRepository = pecaRepository;
        this.lookService = lookService;
        this.tipoPecaRepository = tipoPecaRepository;
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

    @Transactional
    public ResponseEntity<Peca> setTipoPeca(Long pecaID, Long TipoPecaID) {
        try {
            Optional<TipoPeca> tipoPecaOptional = tipoPecaRepository.findById(TipoPecaID);
            if(tipoPecaOptional.isPresent()) {
                Optional<Peca> pecaOptional = pecaRepository.findById(pecaID);
                pecaOptional.ifPresent(peca -> {
                    peca.setCategoriaTipo(tipoPecaOptional.get());
                });
                return  ResponseEntity.ok(pecaOptional.get());
            }
        } catch (Exception e) {
            log.error(e.toString());
            logPecaError(pecaID);
        }
        return ResponseEntity.notFound().build();

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

        lookService.deleteByPecaId(id);

        try {

            return pecaRepository.findById(id)
                    .map((peca -> {
                        peca.setStatus(0);
                        return ResponseEntity.noContent().build();
                    }))
                    .orElseThrow(() -> new IllegalStateException("Peça with ID " + id + " mot found"));
        } catch (Exception e) {
            log.error(e.toString());
            logPecaError(id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    public void logPecaError(Long id) {
        log.info("Could not find Peça with ID: " + id);

    }
}
