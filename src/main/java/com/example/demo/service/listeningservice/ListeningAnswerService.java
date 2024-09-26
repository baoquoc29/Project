package com.example.demo.service.listeningservice;

import com.example.demo.dto.ListeningAnswersDTO;

import java.util.List;

public interface ListeningAnswerService {
    List<ListeningAnswersDTO> getAllListeningAnswers(long id_quiz);

    List<ListeningAnswersDTO> getAnyPartListeningAnswers(Long id_quiz, String part_listening);
}
