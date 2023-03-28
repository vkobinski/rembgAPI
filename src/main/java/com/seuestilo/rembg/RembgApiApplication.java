package com.seuestilo.rembg;

import com.seuestilo.rembg.model.*;
import com.seuestilo.rembg.repository.*;
import com.seuestilo.rembg.service.*;
import com.seuestilo.rembg.storage.StorageService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class RembgApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RembgApiApplication.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService,CorService corService,TamanhoService tamanhoService,MarcaService marcaService, PecaService pecaService, TipoPecaService tipoPecaService, TipoPecaRepository tipoPecaRepository, CategoriaService categoriaService, CategoriaRepository categoriaRepository, UsuarioService usuarioService) {
        return (args) -> {

            adicionaPecas(storageService, corService, tamanhoService, marcaService,pecaService, tipoPecaService, tipoPecaRepository, categoriaService, categoriaRepository);

        };
    }

    private static void adicionaPecas(StorageService storageService, CorService corService, TamanhoService tamanhoService, MarcaService marcaService,PecaService pecaService, TipoPecaService tipoPecaService, TipoPecaRepository tipoPecaRepository, CategoriaService categoriaService, CategoriaRepository categoriaRepository) {
        ArrayList<String> categoriaArrayList = new ArrayList<>(Arrays.asList("pecasuperior", "pecainferior", "pecasobreposicao", "pecaunica", "acessorio", "sapato"));

        for(String s : categoriaArrayList ) {
            Categoria categoria = new Categoria();
            categoria.setDescricao(s);

            categoriaService.criaCategoria(categoria);
        }

        categoriaRepository.findAll().forEach((categoria) -> {

            TipoPeca tipoPeca = new TipoPeca();
            tipoPeca.setCategoria(categoria);
            tipoPecaService.criaTipoPeca(tipoPeca);
        });

        Cor cor = new Cor();
        cor.setDescricao("Rosa");

        Tamanho tamanho = new Tamanho();
        tamanho.setDescricao("GG");

        Marca marca = new Marca();
        marca.setDescricao("Supreme");

        tamanhoService.criaTamanho(tamanho);
        corService.criaCor(cor);
        marcaService.criaMarca(marca);


        //List<TipoPeca> tipoPecaList = tipoPecaRepository.findAll();

       /* storageService.init();

        storageService.loadAll().forEach((caminho) -> {

           Peca peca = new Peca();
           byte[] bytes;

            try {
                bytes = Files.readAllBytes(storageService.loadAsResource(caminho
                        .getFileName().toString()).getFile().toPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Byte[] imagem = ArrayUtils.toObject(bytes);

            if(caminho.startsWith("b")) {
                peca.setImagemComTrat(imagem);
            } else {
                peca.setImagemSemTrat(imagem);
            }

            Random rand = new Random();
            int randomIndex = rand.nextInt(tipoPecaList.size());

            peca.setCategoriaTipo(tipoPecaList.get(randomIndex));


            pecaService.createPeca(peca);
        });*/
    }
}
