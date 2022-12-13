package com.seuestilo.rembg;

import com.seuestilo.rembg.model.Cor;
import com.seuestilo.rembg.model.Peca;
import com.seuestilo.rembg.model.TipoPeca;
import com.seuestilo.rembg.model.Usuario;
import com.seuestilo.rembg.repository.CorRepository;
import com.seuestilo.rembg.repository.PecaRepository;
import com.seuestilo.rembg.repository.TipoPecaRepository;
import com.seuestilo.rembg.repository.UsuarioRepository;
import com.seuestilo.rembg.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RembgApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RembgApiApplication.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService ) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();

        };
    }
}
