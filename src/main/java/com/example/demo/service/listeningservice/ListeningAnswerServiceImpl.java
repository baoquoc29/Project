package com.example.demo.service.listeningservice;

import com.example.demo.dto.ListeningAnswersDTO;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.ListeningRepository;
import com.example.demo.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ListeningAnswerServiceImpl implements ListeningAnswerService {
    @Autowired
    ListeningRepository listeningRepository;

    @Autowired
    QuizRepository quizRepository;

    private final String[] parts = {"Part 1", "Part 2", "Part 3", "Part 4"};

    @Override
    public List<ListeningAnswersDTO> getAllListeningAnswers(long id_quiz) {
        if (!quizRepository.existsById(id_quiz)) {
            throw new AppException(ErrorCode.NOT_FOUND_TOPIC);
        }
        return listeningRepository.findAllListeningAnswersWithContent(id_quiz);
    }

    @Override
    public List<ListeningAnswersDTO> getAnyPartListeningAnswers(Long id_quiz, String part_listening) {
        if (!quizRepository.existsById(id_quiz)) {
            throw new AppException(ErrorCode.NOT_FOUND_TOPIC);
        }

        if (!Arrays.asList(parts).contains(part_listening)) {
            throw new AppException(ErrorCode.NOT_FOUND_PART);
        }

        return listeningRepository.findAnyPartListeningAnswersWithContent(id_quiz, part_listening);
    }
}
