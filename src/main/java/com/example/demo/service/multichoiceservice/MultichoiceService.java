package com.example.demo.service.multichoiceservice;

import com.example.demo.dto.MultipleChoiceAnswerDTO;

import java.util.List;

public interface MultichoiceService {
    List<MultipleChoiceAnswerDTO> listMultipleChoiceAnswers();
    List<MultipleChoiceAnswerDTO> listMultipleChoiceAnswersByQuizId(Long quizId);
}