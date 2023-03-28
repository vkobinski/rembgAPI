package com.seuestilo.rembg.service;

import com.seuestilo.rembg.model.Categoria;
import com.seuestilo.rembg.model.Tamanho;
import com.seuestilo.rembg.repository.CategoriaRepository;
import com.seuestilo.rembg.repository.TamanhoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TamanhoService {

    private final TamanhoRepository tamanhoRepository;


    @Autowired
    public TamanhoService(TamanhoRepository tamanhoRepository) {
        this.tamanhoRepository = tamanhoRepository;
    }

    public ResponseEntity<Tamanho> criaTamanho(Tamanho tamanho) {

        return ResponseEntity.ok(tamanhoRepository.save(tamanho));
    }

    public ResponseEntity<List<Tamanho>> getTamanhos() {
        return ResponseEntity.ok(tamanhoRepository.findAll());
    }

    public ResponseEntity<Tamanho> findTamanhoById(Long id) {
        try {
            return tamanhoRepository.findById(id)
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
