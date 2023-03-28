package com.seuestilo.rembg.service;

import com.seuestilo.rembg.model.Marca;
import com.seuestilo.rembg.model.Tamanho;
import com.seuestilo.rembg.repository.MarcaRepository;
import com.seuestilo.rembg.repository.TamanhoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MarcaService {

    private final MarcaRepository marcaRepository;


    @Autowired
    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    public ResponseEntity<Marca> criaMarca(Marca marca) {

        return ResponseEntity.ok(marcaRepository.save(marca));
    }

    public ResponseEntity<List<Marca>> getMarcas() {
        return ResponseEntity.ok(marcaRepository.findAll());
    }

    public ResponseEntity<Marca> findMarcaById(Long id) {
        try {
            return marcaRepository.findById(id)
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
