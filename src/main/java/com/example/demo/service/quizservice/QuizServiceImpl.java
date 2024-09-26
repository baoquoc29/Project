package com.example.demo.service.quizservice;

import com.example.demo.dto.QuizDTO;
import com.example.demo.entity.Quiz;
import com.example.demo.repository.QuizRepository;
import com.example.demo.repository.UserScoreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    UserScoreRepository scoreRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<QuizDTO> getList() {
        List<Quiz> quiz = new ArrayList<>();
        quiz = quizRepository.findAll();
        return quiz.stream().map(quizList -> modelMapper.map(quizList, QuizDTO.class)).collect(Collectors.toList());
    }
}
