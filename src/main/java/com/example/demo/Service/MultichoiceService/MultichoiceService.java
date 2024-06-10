package com.example.demo.Service.MultichoiceService;

import com.example.demo.DTO.MultipleChoiceAnswerDTO;

import java.util.List;

public interface MultichoiceService {
    List<MultipleChoiceAnswerDTO> listMultipleChoiceAnswers();
    List<MultipleChoiceAnswerDTO> listMultipleChoiceAnswersByQuizId(Long quizId);
}
