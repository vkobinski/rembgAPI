package com.seuestilo.rembg.service;

import com.seuestilo.rembg.model.Categoria;
import com.seuestilo.rembg.model.Cor;
import com.seuestilo.rembg.repository.CategoriaRepository;
import com.seuestilo.rembg.repository.CorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CorService {

    private final CorRepository corRepository;


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
        try {
            return corRepository.findById(id)
                    .map(ResponseEntity::ok)
                    .orElseThrow(() -> {
                        throw new IllegalStateException("Could not find Categoria with the given ID");
                    });
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.notFound().build();
        }
    }


}
