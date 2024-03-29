package com.seuestilo.rembg.controller;

import com.seuestilo.rembg.python.PythonExec;
import com.seuestilo.rembg.storage.StorageFileNotFoundException;
import com.seuestilo.rembg.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.stream.Collectors;

@Controller
public class FileUploaderController {

    private final StorageService storageService;
    private final PythonExec pythonExec;

    @Autowired
    public FileUploaderController(StorageService storageService, PythonExec pythonExec) {
        this.storageService = storageService;
        this.pythonExec = pythonExec;
    }

    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {
        model.addAttribute("files", storageService.loadAll().map(
                path ->
                        MvcUriComponentsBuilder.fromMethodName(FileUploaderController.class,
                                "serveFile",
                                path.getFileName().toString()).build().toUri().toString()).collect(Collectors.toList()));
        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        //return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() +
         //       "\"").body(file);

        return ResponseEntity.ok(file);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException, InterruptedException {
        storageService.store(file);
        pythonExec.removeBackGround(file);

        return "redirect:/files/" + "b" + file.getOriginalFilename();
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}