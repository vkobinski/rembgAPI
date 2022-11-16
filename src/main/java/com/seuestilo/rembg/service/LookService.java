package com.seuestilo.rembg.service;

import com.seuestilo.rembg.model.Look;
import com.seuestilo.rembg.repository.LookRepository;
import com.seuestilo.rembg.repository.PecaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class LookService {

    private final LookRepository lookRepository;
    private final PecaRepository pecaRepository;

    @Autowired
    public LookService(LookRepository lookRepository, PecaRepository pecaRepository) {
        this.lookRepository = lookRepository;
        this.pecaRepository = pecaRepository;
    }

    @Transactional
    public Optional<ResponseEntity<Object>> deleteByPecaId(Long idPeca) {
         return lookRepository.findById(idPeca).map((look -> {
            if (Objects.equals(look.getPecaInferior().getPecaID(), idPeca)) {
                look.setStatus(0);
            }
            if (Objects.equals(look.getPecaSuperior().getPecaID(), idPeca)) {
                look.setStatus(0);
            }
            if (Objects.equals(look.getPecaSobreposicao().getPecaID(), idPeca)) {
                look.setStatus(0);
            }


             return ResponseEntity.noContent().build();
         }));


    }

    public ResponseEntity<Look> criaLook(Look look) {

        return ResponseEntity.ok(lookRepository.save(look));
    }

    public ResponseEntity<Look> findLookById(Long id) {
        Optional<Look> optionalLook = lookRepository.findById(id);
        return optionalLook.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
