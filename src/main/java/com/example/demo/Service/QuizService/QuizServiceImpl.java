package com.example.demo.Service.QuizService;

import com.example.demo.DTO.QuizDTO;
import com.example.demo.Entity.Quiz;
import com.example.demo.Repository.QuizRepository;
import com.example.demo.Repository.UserScoreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        List<QuizDTO> quizDTOList = new ArrayList<>();
        for(Quiz quiz1 : quiz){
            quizDTOList.add(modelMapper.map(quiz1, QuizDTO.class));
        }
        return quizDTOList;
    }


}
