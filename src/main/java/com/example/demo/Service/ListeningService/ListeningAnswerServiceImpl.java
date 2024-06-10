package com.example.demo.Service.ListeningService;

import com.example.demo.DTO.ListeningAnswersDTO;
import com.example.demo.Repository.ListeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListeningAnswerServiceImpl implements ListeningAnswerService {
    @Autowired
    ListeningRepository listeningRepository;
    @Override
    public List<ListeningAnswersDTO> getAllListeningAnswers() {
        return listeningRepository.findAllListeningAnswersWithContent();
    }

    @Override
    public List<ListeningAnswersDTO> getAnyPartListeningAnswers(Long id_quiz, String part_listening) {
        return listeningRepository.findAnyPartListeningAnswersWithContent(id_quiz, part_listening);
    }

}
