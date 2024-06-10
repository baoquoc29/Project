package com.example.demo.Service.ListeningService;

import com.example.demo.DTO.ListeningAnswersDTO;

import java.util.List;

public interface ListeningAnswerService {
    List<ListeningAnswersDTO> getAllListeningAnswers();
    List<ListeningAnswersDTO> getAnyPartListeningAnswers(Long id_quiz,String part_listening);
}
