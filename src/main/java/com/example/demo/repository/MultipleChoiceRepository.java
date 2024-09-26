package com.example.demo.repository;

import com.example.demo.dto.MultipleChoiceAnswerDTO;
import com.example.demo.entity.MultipleChoiceAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MultipleChoiceRepository extends JpaRepository<MultipleChoiceAnswers,Long> {
    @Query("Select new com.example.demo.dto.MultipleChoiceAnswerDTO(ma.idAnswer,q.idQuiz,mq.idMultipleQuestion,mq.content,q.title,ma.optionA,ma.optionB,ma.optionC,ma.optionD,ma.answerCorrect,ma.answerDescription,mq.part) from MultipleChoiceAnswers ma join ma.question mq join ma.quiz q")
    List<MultipleChoiceAnswerDTO> findMultipleChoiceAnswer();

    @Query("Select new com.example.demo.dto.MultipleChoiceAnswerDTO(ma.idAnswer,q.idQuiz,mq.idMultipleQuestion,mq.content,q.title,ma.optionA,ma.optionB,ma.optionC,ma.optionD,ma.answerCorrect,ma.answerDescription,mq.part) from MultipleChoiceAnswers ma join ma.question mq join ma.quiz q WHERE q.idQuiz = :id_quiz ")
    List<MultipleChoiceAnswerDTO> findMultipleChoiceAnswerByQuiz(@Param("id_quiz") Long id_quiz);
}
