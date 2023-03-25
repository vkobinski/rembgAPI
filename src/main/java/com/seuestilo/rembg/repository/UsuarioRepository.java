package com.seuestilo.rembg.repository;

import com.seuestilo.rembg.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findUsuarioByEmail(String email);
}