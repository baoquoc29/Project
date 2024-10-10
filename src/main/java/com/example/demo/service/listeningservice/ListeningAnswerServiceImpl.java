package com.example.demo.service.listeningservice;

import com.example.demo.dto.ListeningAnswersDTO;
import com.example.demo.entity.ListeningAnswers;
import com.example.demo.entity.ListeningQuestions;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.ListeningQuestionRepository;
import com.example.demo.repository.ListeningRepository;
import com.example.demo.repository.QuizRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ListeningAnswerServiceImpl implements ListeningAnswerService {
    @Autowired
    ListeningRepository listeningRepository;

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ListeningQuestionRepository listeningQuestionRepository;
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

    @Override
    public ListeningAnswersDTO getListeningAnswersById(Long idQuestion) {
        return listeningRepository.findAnyPartListeningAnswersById(idQuestion);
    }

    @Override
    public List<ListeningAnswersDTO> getPagePartListening(Long id_quiz, String part_listening, Pageable pageable) {
        if (!quizRepository.existsById(id_quiz)) {
            throw new AppException(ErrorCode.NOT_FOUND_TOPIC);
        }

        if (!Arrays.asList(parts).contains(part_listening)) {
            throw new AppException(ErrorCode.NOT_FOUND_PART);
        }

        return listeningRepository.findAnyPageWithPageable(id_quiz, part_listening,pageable).getContent();
    }

    @Override
    public ListeningAnswersDTO updateListeningByID(Long idQuestion, ListeningAnswersDTO listeningAnswersDTO) {
        ListeningAnswersDTO listeningAnswersDTO1 = listeningRepository.findAnyPartListeningAnswersById(idQuestion);

        // Bật tính năng bỏ qua các trường null
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        modelMapper.map(listeningAnswersDTO, listeningAnswersDTO1);

        ListeningAnswers listeningAnswers = modelMapper.map(listeningAnswersDTO1, ListeningAnswers.class);
        ListeningQuestions listeningQuestions = modelMapper.map(listeningAnswersDTO1, ListeningQuestions.class);

        ListeningAnswers saveAnswer = listeningRepository.save(listeningAnswers);
        ListeningQuestions saveQuestion = listeningQuestionRepository.save(listeningQuestions);

        modelMapper.map(saveAnswer, listeningAnswersDTO);
        modelMapper.map(saveQuestion, listeningAnswersDTO);

        return listeningAnswersDTO;
    }

}
