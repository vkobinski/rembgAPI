package com.seuestilo.rembg.service;

import com.seuestilo.rembg.model.Calendario;
import com.seuestilo.rembg.model.Usuario;
import com.seuestilo.rembg.repository.CalendarioRepository;
import com.seuestilo.rembg.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CalendarioService {

    private final CalendarioRepository calendarioRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public CalendarioService(CalendarioRepository calendarioRepository, UsuarioRepository usuarioRepository) {
        this.calendarioRepository = calendarioRepository;
        this.usuarioRepository = usuarioRepository;
    }


    public ResponseEntity<Calendario> criaCalendario(Calendario calendario) {

        return ResponseEntity.ok(calendarioRepository.save(calendario));
    }

    public ResponseEntity<List<Calendario>> getCalendarios() {
        return ResponseEntity.ok(calendarioRepository.findAll());
    }

    public ResponseEntity<Calendario> findCalendarioById(Long id) {
        try {
            return calendarioRepository.findById(id)
                    .map(ResponseEntity::ok)
                    .orElseThrow(() -> {
                        throw new IllegalStateException("Could not find Calendario with the given ID");
                    });
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.notFound().build();
        }
    }

    public List<Calendario> findCalendariosByUserId(Long usuarioId) {
        Optional<Usuario> byId = usuarioRepository.findById(usuarioId);
        return byId.map(calendarioRepository::findCalendariosByUsuario).orElse(null);
    }

    public Optional<Calendario> createCalendarioByUserId(Long userId, Calendario calendario) {
        Optional<Usuario> byId = usuarioRepository.findById(userId);

        if(byId.isPresent()) {
            calendario.setUsuario(byId.get());
            Calendario save = calendarioRepository.save(calendario);
            return Optional.of(save);
        }
        return Optional.empty();
    }

}
