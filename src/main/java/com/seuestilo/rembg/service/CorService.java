package com.seuestilo.rembg.service;

import com.seuestilo.rembg.model.Cor;
import com.seuestilo.rembg.repository.CorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
