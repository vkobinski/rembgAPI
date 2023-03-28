package com.seuestilo.rembg.controller;

import com.seuestilo.rembg.model.Usuario;
import com.seuestilo.rembg.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuario")
@Slf4j
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/auth")
    public ResponseEntity<Usuario> authUserByEmail(@RequestBody Usuario usuario) {
        Usuario user = usuarioService.authByEmail(usuario.getEmail(), usuario.getSenha());

        if(user != null) return ResponseEntity.ok(user);
        else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Object> cadastraUsuario(@RequestBody Usuario usuario) {
        return usuarioService.criaUsuario(usuario);
    }

}
