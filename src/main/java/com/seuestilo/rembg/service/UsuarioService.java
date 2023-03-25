package com.seuestilo.rembg.service;

import com.seuestilo.rembg.model.Usuario;
import com.seuestilo.rembg.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public ResponseEntity<Usuario> criaUsuario(Usuario usuario) {
        return ResponseEntity.ok(usuarioRepository.save(usuario));
    }

    public ResponseEntity<List<Usuario>> getUsuarios() {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    public ResponseEntity<Usuario> findUsuarioById(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            return ResponseEntity.ok(usuarioOptional.get());
        } else {
            log.error("User not found with the given ID");
            return ResponseEntity.notFound().build();
        }
    }

    public Usuario authByEmail(String email, String senha) {

        Optional<Usuario> usuarioOptional = usuarioRepository.findUsuarioByEmail(email);
        if(usuarioOptional.isPresent()) {
            if (usuarioOptional.get().getSenha().equals(senha)) return usuarioOptional.get();
        }

        return null;
    }


}