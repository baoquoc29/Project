package com.example.demo.service.importfileservice;

import com.example.demo.dto.QuizDTO;

import java.io.IOException;

public interface ImportFileService {
    public void readExcelFile(String filePath, QuizDTO quizDTO) throws IOException;
}
