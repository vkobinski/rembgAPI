package com.seuestilo.rembg.repository;

import com.seuestilo.rembg.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}