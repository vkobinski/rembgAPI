package com.seuestilo.rembg.repository;

import com.seuestilo.rembg.model.Peca;
import com.seuestilo.rembg.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PecaRepository extends JpaRepository<Peca, Long> {

    public List<Peca> getPecasByUsuario(Usuario usuario);
}
