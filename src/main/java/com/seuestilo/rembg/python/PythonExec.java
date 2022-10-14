package com.seuestilo.rembg.python;


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

    private StorageService storageService;

    @Autowired
    public PythonExec(StorageService storageService) {
        this.storageService = storageService;
    }

    public void removeBackGround(MultipartFile file) throws IOException, InterruptedException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
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

        File file1 = new File("b"+file.getOriginalFilename());

        try(OutputStream outputStream = new FileOutputStream(file1)){
            IOUtils.copy(responseEntity.getContent(), outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        storageService.store(file1);

        file1.delete();
    }

}
