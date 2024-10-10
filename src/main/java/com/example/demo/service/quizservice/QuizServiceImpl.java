package com.example.demo.service.quizservice;

import com.example.demo.dto.QuizDTO;
import com.example.demo.entity.Quiz;
import com.example.demo.repository.QuizRepository;
import com.example.demo.repository.UserScoreRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    @Override
    public QuizDTO updateByIdQuiz(QuizDTO quizDTO, Long idQuiz) {
        Optional<Quiz> optionalQuiz = quizRepository.findById(idQuiz);

        if (optionalQuiz.isPresent()) {
            Quiz quizEntity = optionalQuiz.get();
            modelMapper.getConfiguration().setSkipNullEnabled(true);
            modelMapper.map(quizDTO, quizEntity);
            System.out.println(quizEntity);
            Quiz savedQuiz = quizRepository.save(quizEntity);
            return modelMapper.map(savedQuiz, QuizDTO.class);
        } else {
            throw new RuntimeException("Quiz not found with ID: " + idQuiz);
        }
    }


    @Override
    public QuizDTO getByIdQuiz(Long idQuiz) {
        return modelMapper.map(quizRepository.findById(idQuiz).get(), QuizDTO.class);
    }

    @Override
    public List<QuizDTO> getAllListByPagable(Pageable pageable) {
        List<Quiz> list = quizRepository.findAll(pageable).getContent();
        return list.stream().map(allList-> modelMapper.map(allList,QuizDTO.class)).collect(Collectors.toList());
    }

}
