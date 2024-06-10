package com.example.demo.Repository;

import com.example.demo.DTO.MultipleChoiceAnswerDTO;
import com.example.demo.Entity.MultipleChoiceAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MultipleChoiceRepository extends JpaRepository<MultipleChoiceAnswer,Long> {
    @Query("Select new com.example.demo.DTO.MultipleChoiceAnswerDTO(ma.idAnswer,q.idQuiz,mq.idMultipleQuestion,mq.content,q.title,ma.optionA,ma.optionB,ma.optionC,ma.optionD,ma.answerCorrect,ma.answerDescription,mq.part) from MultipleChoiceAnswer ma join ma.question mq join ma.quiz q")
    List<MultipleChoiceAnswerDTO> findMultipleChoiceAnswer();

    @Query("Select new com.example.demo.DTO.MultipleChoiceAnswerDTO(ma.idAnswer,q.idQuiz,mq.idMultipleQuestion,mq.content,q.title,ma.optionA,ma.optionB,ma.optionC,ma.optionD,ma.answerCorrect,ma.answerDescription,mq.part) from MultipleChoiceAnswer ma join ma.question mq join ma.quiz q WHERE q.idQuiz = :id_quiz ")
    List<MultipleChoiceAnswerDTO> findMultipleChoiceAnswerByQuiz(@Param("id_quiz") Long id_quiz);
}
