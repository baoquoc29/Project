package com.example.demo.service.quizservice;

import com.example.demo.dto.QuizDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuizService {


     List<QuizDTO> getList();

    QuizDTO updateByIdQuiz(QuizDTO quizDTO,Long idQuiz);

    QuizDTO getByIdQuiz(Long idQuiz);

    List<QuizDTO> getAllListByPagable(Pageable pageable);
}
