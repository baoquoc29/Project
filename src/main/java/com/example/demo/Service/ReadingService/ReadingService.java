package com.example.demo.Service.ReadingService;

import com.example.demo.DTO.ReadingAnswerDTO;

import java.util.List;

public interface ReadingService {
    List<ReadingAnswerDTO> listReadingAnswers();
    List<ReadingAnswerDTO> listReadingAnswersByPartAndQuiz(Long id,String part);
}
