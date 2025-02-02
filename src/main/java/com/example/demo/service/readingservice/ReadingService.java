package com.example.demo.service.readingservice;

import com.example.demo.dto.ReadingAnswerDTO;

import java.util.List;

public interface ReadingService {
    List<ReadingAnswerDTO> listReadingAnswersByPartAndQuiz(Long id, String part);

    ReadingAnswerDTO getReadingAnswerById(Long id);
    ReadingAnswerDTO updateReadingAnswer(Long id,ReadingAnswerDTO readingAnswerDTO);
}
