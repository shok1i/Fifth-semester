package com.example.demo.services;

import com.example.demo.models.Pdf;
import com.example.demo.repositories.PdfRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PdfService {
    private final PdfRepository pdfRepository;

    public void save(MultipartFile file) throws IOException {
        Pdf pdf = new Pdf();

        pdf.setFileName(file.getOriginalFilename());
        pdf.setFileData(file.getBytes());
        pdf.setFileType(file.getContentType());

        pdfRepository.save(pdf);
    }

    public Pdf getPdf(Long id) {
        return pdfRepository.findById(id).orElse(null);
    }
}
