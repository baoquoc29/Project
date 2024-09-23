package com.example.demo.Repository;

import com.example.demo.DTO.ReadingAnswerDTO;
import com.example.demo.Entity.ReadingAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReadingRepository extends JpaRepository<ReadingAnswers,Long> {

    @Query("SELECT new com.example.demo.DTO.ReadingAnswerDTO(ra.idAnswer, ra.idReadingContent,q.idQuiz,q.title, ra.optionA, ra.optionB, ra.optionC, ra.optionD, ra.answerCorrect,ra.answerDescription, ra.question, rc.content, rc.part) " +
            "FROM ReadingAnswers ra " +
            "JOIN ra.readingQuestions rc JOIN ra.quiz q order by ra.idAnswer")
    List<ReadingAnswerDTO> findAllReadingAnswersWithContent();

    @Query("SELECT new com.example.demo.DTO.ReadingAnswerDTO(ra.idAnswer, ra.idReadingContent,q.idQuiz,q.title, ra.optionA, ra.optionB, ra.optionC, ra.optionD, ra.answerCorrect,ra.answerDescription, ra.question, rc.content, rc.part) " +
            "FROM ReadingAnswers ra " +
            "JOIN ra.readingQuestions rc JOIN ra.quiz q WHERE q.idQuiz = :id_quiz AND rc.part = :part_reading order by ra.idAnswer")
    List<ReadingAnswerDTO> findAllReadingAnswersWithContentByQuizAndPart(@Param("id_quiz") Long idQuiz, @Param("part_reading") String part);

}
