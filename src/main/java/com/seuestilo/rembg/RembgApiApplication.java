package com.seuestilo.rembg;

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
	CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
}
