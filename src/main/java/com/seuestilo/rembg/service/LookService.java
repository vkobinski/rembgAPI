package com.seuestilo.rembg.service;

import com.seuestilo.rembg.model.Look;
import com.seuestilo.rembg.model.Peca;
import com.seuestilo.rembg.model.Usuario;
import com.seuestilo.rembg.repository.LookRepository;
import com.seuestilo.rembg.repository.PecaRepository;
import com.seuestilo.rembg.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Slf4j
public class LookService {

    private final LookRepository lookRepository;
    private final PecaRepository pecaRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public LookService(LookRepository lookRepository, PecaRepository pecaRepository, UsuarioRepository usuarioRepository) {
        this.lookRepository = lookRepository;
        this.pecaRepository = pecaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Optional<ResponseEntity<Object>> deleteByLookId(Long idLook) {
         return lookRepository.findById(idLook).map((look -> {
            if (Objects.equals(look.getPecaInferior().getPecaID(), idLook)) {
                look.setStatus(0);
            }
            if (Objects.equals(look.getPecaSuperior().getPecaID(), idLook)) {
                look.setStatus(0);
            }
            if (Objects.equals(look.getPecaSobreposicao().getPecaID(), idLook)) {
                look.setStatus(0);
            }

            for (Peca peca : look.getAcessorios()) {
                peca.setStatus(0);
            }

             return ResponseEntity.noContent().build();
         }));
    }

    public ResponseEntity<Object> deleteLook(Long id) {
        try {
            lookRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    public ResponseEntity<Look> criaLook(Look look) {

        Set<Peca> pecaSet = new HashSet<>();

        for (Peca peca : look.getAcessorios()) {
            if(peca == null) continue;

            Optional<Peca> pecaOptional = pecaRepository.findById(peca.getPecaID());
            pecaOptional.ifPresent(pecaSet::add);
        }

        look.setAcessorios(pecaSet);

        if(look.getPecaSuperior() != null) {
            Optional<Peca> pecaOptional = pecaRepository.findById(look.getPecaSuperior().getPecaID());
            pecaOptional.ifPresent(look::setPecaSuperior);
        }

        if(look.getPecaInferior() != null) {
            Optional<Peca> pecaOptional = pecaRepository.findById(look.getPecaInferior().getPecaID());
            pecaOptional.ifPresent(look::setPecaInferior);
        }

        if(look.getPecaUnica() != null) {
            Optional<Peca> pecaOptional = pecaRepository.findById(look.getPecaUnica().getPecaID());
            pecaOptional.ifPresent(look::setPecaUnica);
        }

        if(look.getPecaSobreposicao() != null) {
            Optional<Peca> pecaOptional = pecaRepository.findById(look.getPecaSobreposicao().getPecaID());
            pecaOptional.ifPresent(look::setPecaSobreposicao);
        }

        return ResponseEntity.ok(lookRepository.save(look));
    }

    public ResponseEntity<Look> findLookById(Long id) {
        Optional<Look> optionalLook = lookRepository.findById(id);
        return optionalLook.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<Look>> getLooks() {
        return ResponseEntity.ok(lookRepository.findAll());
    }

    public ResponseEntity<List<Look>> getLookByUsuario(Long userId) {
        Optional<Usuario> u = usuarioRepository.findById(userId);
        return u.map(usuario -> ResponseEntity.ok(lookRepository.getLookByUsuario(usuario))).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
