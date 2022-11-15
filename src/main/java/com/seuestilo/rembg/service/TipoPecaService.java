package com.seuestilo.rembg.service;

import com.seuestilo.rembg.model.Categoria;
import com.seuestilo.rembg.model.TipoPeca;
import com.seuestilo.rembg.repository.CategoriaRepository;
import com.seuestilo.rembg.repository.TipoPecaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TipoPecaService {

    private final TipoPecaRepository tipoPecaRepository;
    private final CategoriaRepository categoriaRepository;

    @Autowired
    public TipoPecaService(TipoPecaRepository tipoPecaRepository, CategoriaRepository categoriaRepository) {
        this.tipoPecaRepository = tipoPecaRepository;
        this.categoriaRepository = categoriaRepository;
    }


    public ResponseEntity<TipoPeca> criaTipoPeca(TipoPeca tipoPeca) {

        return ResponseEntity.ok(tipoPecaRepository.save(tipoPeca));
    }

    public ResponseEntity<List<TipoPeca>> getTipoPecas() {
        return ResponseEntity.ok(tipoPecaRepository.findAll());
    }

    @Transactional
    public ResponseEntity<TipoPeca> setCategoria(Long idTipo, Long idCategoria) {
        try {
            Optional<TipoPeca> tipopecaOptional = tipoPecaRepository.findById(idTipo);
            if (tipopecaOptional.isPresent()) {
                Optional<Categoria> optionalCategoria = categoriaRepository.findById(idCategoria);
                optionalCategoria.ifPresent(categoria -> tipopecaOptional.get().setCategoria(categoria));
                return ResponseEntity.ok(tipopecaOptional.get());
            }
        } catch (Exception e) {
            log.error(e.toString());
            log.info("Could not find TipoPeca or Categoria with the given ID");
        }

        return ResponseEntity.notFound().build();
    }

}
