package com.example.demo.controllers;

import com.example.demo.models.Pdf;
import com.example.demo.services.PdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pdf")
public class PdfController {
    private final PdfService pdfService;

    // POST-запрос
    @PostMapping("/upload")
    public ResponseEntity<String> uploadPdf(@RequestParam("file") MultipartFile file) {
        try {
            pdfService.save(file);
            return new ResponseEntity<>("Файл загружен в базу данных успешно!", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Ошибка загрузки файла", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET-запрос
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable("id") Long id) {
        Pdf pdf = pdfService.getPdf(id);

        if (pdf != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", pdf.getFileName());

            return new ResponseEntity<>(pdf.getFileData(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
