package com.example.demo.controller;

import com.example.demo.dto.QuizDTO;
import com.example.demo.service.importfileservice.ImportFileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/import")
public class ImportFileController {

    @Autowired
    private ImportFileService importFileService;

    @PostMapping("/excel")
    public ResponseEntity<String> importExcelFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("quizDTO") String quizDTOJson) { // Change here

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a file!");
        }

        try {

            ObjectMapper objectMapper = new ObjectMapper();
            QuizDTO quizDTO = objectMapper.readValue(quizDTOJson, QuizDTO.class);

            String filePath = saveFile(file);
            importFileService.readExcelFile(filePath, quizDTO);
            return ResponseEntity.ok("File imported successfully");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("File import failed: " + e.getMessage());
        }
    }


    private String saveFile(MultipartFile file) throws IOException {
        String filePath = System.getProperty("user.dir") + "/uploads/" + file.getOriginalFilename();
        file.transferTo(new java.io.File(filePath));
        return filePath;
    }
}
