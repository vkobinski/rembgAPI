package com.seuestilo.rembg.python;


import com.seuestilo.rembg.model.*;
import com.seuestilo.rembg.repository.*;
import com.seuestilo.rembg.service.PecaService;
import com.seuestilo.rembg.storage.StorageService;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Optional;

@Service
public class PythonExec {

    private final StorageService storageService;
    private final PecaService pecaService;
    private final UsuarioRepository usuarioRepository;
    private final TipoPecaRepository tipoPecaRepository;
    private final CategoriaRepository categoriaRepository;
    private final TamanhoRepository tamanhoRepository;
    private final CorRepository corRepository;
    private final MarcaRepository marcaRepository;

    @Autowired
    public PythonExec(StorageService storageService, PecaService pecaService, UsuarioRepository usuarioRepository, TipoPecaRepository tipoPecaRepository, CategoriaRepository categoriaRepository, TamanhoRepository tamanhoRepository, CorRepository corRepository, MarcaRepository marcaRepository) {
        this.storageService = storageService;
        this.pecaService = pecaService;
        this.usuarioRepository = usuarioRepository;
        this.tipoPecaRepository = tipoPecaRepository;
        this.categoriaRepository = categoriaRepository;
        this.tamanhoRepository = tamanhoRepository;
        this.corRepository = corRepository;
        this.marcaRepository = marcaRepository;
    }

    public void removeBackGround(MultipartFile file, Peca peca) throws IOException {

        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {

            HttpPost uploadFile = new HttpPost("http://localhost:5000");
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addTextBody("field1", "yes", ContentType.TEXT_PLAIN);

            Optional<Categoria> categoria = categoriaRepository.findById(peca.getCategoriaTipo().getCategoria().getCategoriaID());

            if (categoria.isPresent()) {
                Optional<TipoPeca> tipoPeca = tipoPecaRepository.getTipoPecasByCategoria(categoria.get());
                tipoPeca.ifPresent(peca::setCategoriaTipo);
            }

            Optional<Tamanho> tamanho = tamanhoRepository.findById(peca.getTamanho().getTamanhoID());
            tamanho.ifPresent(peca::setTamanho);

            Optional<Cor> cor = corRepository.findById(peca.getCor().getCorID());
            cor.ifPresent(peca::setCor);

            Optional<Marca> marca = marcaRepository.findById((peca.getMarca().getMarcaId()));
            marca.ifPresent(peca::setMarca);


            Resource resource = storageService.loadAsResource(file.getOriginalFilename());
            File f = resource.getFile();
            builder.addBinaryBody(
                    "file",
                    new FileInputStream(f),
                    ContentType.APPLICATION_OCTET_STREAM,
                    f.getName()
            );


            peca.setImagemSemTrat(convertFileToBytes(f));

            HttpEntity multipart = builder.build();
            uploadFile.setEntity(multipart);
            CloseableHttpResponse response = httpClient.execute(uploadFile);
            HttpEntity responseEntity = response.getEntity();

            File file1 = new File("b" + file.getOriginalFilename());

            try (OutputStream outputStream = new FileOutputStream(file1)) {
                IOUtils.copy(responseEntity.getContent(), outputStream);

            } catch (IOException e) {
                e.printStackTrace();
            }

            File file2 = storageService.store(file1);
            peca.setImagemComTrat(convertFileToBytes(file2));

            pecaService.createPeca(peca);

            file1.delete();
        }
    }

    public Byte[] byteToByte(MultipartFile file) throws IOException {
        byte[] byteArray = file.getBytes();
        Byte[] byteObjectArray = new Byte[byteArray.length];

        for (int i = 0; i < byteArray.length; i++) {
            byteObjectArray[i] = byteArray[i];
        }

        return byteObjectArray;
    }

    public Byte[] byteToByte(byte[] byteArray)  {
        Byte[] byteObjectArray = new Byte[byteArray.length];

        for (int i = 0; i < byteArray.length; i++) {
            byteObjectArray[i] = byteArray[i];
        }

        return byteObjectArray;
    }

    public static byte[] convertFileToBytes(File file) throws IOException {
        try(FileInputStream inputStream = new FileInputStream(file)) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[10000];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            return outputStream.toByteArray();
        }
    }


}
