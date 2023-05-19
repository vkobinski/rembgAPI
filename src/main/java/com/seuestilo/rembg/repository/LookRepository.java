package com.seuestilo.rembg.repository;

import com.seuestilo.rembg.model.Look;
import com.seuestilo.rembg.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LookRepository extends JpaRepository<Look, Long> {

    public List<Look> getLookByUsuario(Usuario usuario);
}