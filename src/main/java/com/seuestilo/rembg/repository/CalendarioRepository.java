package com.seuestilo.rembg.repository;

import com.seuestilo.rembg.model.Calendario;
import com.seuestilo.rembg.model.Categoria;
import com.seuestilo.rembg.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Calendar;
import java.util.List;

public interface CalendarioRepository extends JpaRepository<Calendario, Long> {

    public List<Calendario> findCalendariosByUsuario(Usuario usuario);
}