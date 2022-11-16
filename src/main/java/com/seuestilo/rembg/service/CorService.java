package com.seuestilo.rembg.service;

import com.seuestilo.rembg.model.Cor;
import com.seuestilo.rembg.repository.CorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CorService {

    protected final CorRepository corRepository;


    @Autowired
    public CorService(CorRepository corRepository) {
        this.corRepository = corRepository;
    }

    public ResponseEntity<Cor> criaCor(Cor cor) {

        return ResponseEntity.ok(corRepository.save(cor));
    }

    public ResponseEntity<List<Cor>> getCores() {

        return ResponseEntity.ok(corRepository.findAll());
    }

    public ResponseEntity<Cor> findCorById(Long id) {
        Optional<Cor> corOptional = corRepository.findById(id);

        if(corOptional.isPresent()) {
            return ResponseEntity.ok(corOptional.get());
        } else {
            log.info("Could not found Cor with the given ID");
            return ResponseEntity.notFound().build();
        }
    }
}
