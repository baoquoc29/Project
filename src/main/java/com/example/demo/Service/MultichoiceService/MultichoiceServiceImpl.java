package com.example.demo.Service.MultichoiceService;

import com.example.demo.DTO.MultipleChoiceAnswerDTO;
import com.example.demo.Repository.MultipleChoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MultichoiceServiceImpl implements MultichoiceService {
    @Autowired
    MultipleChoiceRepository multipleChoiceRepository;
    @Override
    public List<MultipleChoiceAnswerDTO> listMultipleChoiceAnswers() {
        return multipleChoiceRepository.findMultipleChoiceAnswer();
    }

    @Override
    public List<MultipleChoiceAnswerDTO> listMultipleChoiceAnswersByQuizId(Long quizId) {
        return multipleChoiceRepository.findMultipleChoiceAnswerByQuiz(quizId);
    }
}
