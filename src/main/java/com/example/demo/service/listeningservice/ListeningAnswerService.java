package com.example.demo.service.listeningservice;

import com.example.demo.dto.ListeningAnswersDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ListeningAnswerService {
    List<ListeningAnswersDTO> getAllListeningAnswers(long id_quiz);

    List<ListeningAnswersDTO> getAnyPartListeningAnswers(Long id_quiz, String part_listening);

    ListeningAnswersDTO getListeningAnswersById(Long idQuestion);

    List<ListeningAnswersDTO> getPagePartListening(Long id_quiz, String part_listening, Pageable pageable);

    ListeningAnswersDTO updateListeningByID(Long idQuestion,ListeningAnswersDTO listeningAnswersDTO);
}
