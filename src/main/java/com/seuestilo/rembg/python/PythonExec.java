package com.seuestilo.rembg.python;


import com.seuestilo.rembg.model.Peca;
import com.seuestilo.rembg.repository.TipoPecaRepository;
import com.seuestilo.rembg.repository.UsuarioRepository;
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

@Service
public class PythonExec {

    private final StorageService storageService;
    private final PecaService pecaService;
    private final UsuarioRepository usuarioRepository;
    private final TipoPecaRepository tipoPecaRepository;

    @Autowired
    public PythonExec(StorageService storageService, PecaService pecaService, UsuarioRepository usuarioRepository, TipoPecaRepository tipoPecaRepository) {
        this.storageService = storageService;
        this.pecaService = pecaService;
        this.usuarioRepository = usuarioRepository;
        this.tipoPecaRepository = tipoPecaRepository;
    }

    public void removeBackGround(MultipartFile file, Long userId) throws IOException {

        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {

            HttpPost uploadFile = new HttpPost("http://localhost:5000");
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addTextBody("field1", "yes", ContentType.TEXT_PLAIN);

            Resource resource = storageService.loadAsResource(file.getOriginalFilename());
            File f = resource.getFile();
            builder.addBinaryBody(
                    "file",
                    new FileInputStream(f),
                    ContentType.APPLICATION_OCTET_STREAM,
                    f.getName()
            );

            HttpEntity multipart = builder.build();
            uploadFile.setEntity(multipart);
            CloseableHttpResponse response = httpClient.execute(uploadFile);
            HttpEntity responseEntity = response.getEntity();

            File file1 = new File("b" + file.getOriginalFilename());

            Peca peca = new Peca();
            peca.setImagemComTrat(byteToByte(convertFileToBytes(f)));
            peca.setImagemSemTrat(byteToByte(file));

            // TODO: REMOVER ESSE VALOR TRUNCADO
            peca.setCategoriaTipo(tipoPecaRepository.findById(1L).get());

            usuarioRepository.findById(userId).ifPresent(peca::setUsuario);

            pecaService.createPeca(peca);

            try (OutputStream outputStream = new FileOutputStream(file1)) {
                IOUtils.copy(responseEntity.getContent(), outputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }

            storageService.store(file1);

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
