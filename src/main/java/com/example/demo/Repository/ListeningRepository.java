package com.example.demo.Repository;

import com.example.demo.DTO.ListeningAnswersDTO;
import com.example.demo.Entity.ListeningAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ListeningRepository extends JpaRepository<ListeningAnswers, Long> {

        @Query("SELECT new com.example.demo.DTO.ListeningAnswersDTO(la.idAnswer, la.idContent,q.idQuiz,q.title, la.optionA, la.optionB, la.optionC, la.optionD, la.answerCorrect, la.question, lc.image, lc.audio, lc.partListening) " +
                "FROM ListeningAnswers la " +
                "JOIN la.listeningContent lc JOIN la.quiz q")
        List<ListeningAnswersDTO> findAllListeningAnswersWithContent();
        @Query("SELECT new com.example.demo.DTO.ListeningAnswersDTO(la.idAnswer, la.listeningContent.idContent, q.idQuiz, q.title, la.optionA, la.optionB, la.optionC, la.optionD, la.answerCorrect, la.question, la.listeningContent.image, la.listeningContent.audio, la.listeningContent.partListening) " +
                "FROM ListeningAnswers la " +
                "JOIN la.listeningContent lc " +
                "JOIN la.quiz q " +
                "WHERE q.idQuiz = :id_quiz AND lc.partListening = :part_listening")
        List<ListeningAnswersDTO> findAnyPartListeningAnswersWithContent(@Param("id_quiz") Long idQuiz, @Param("part_listening") String partListening);



}

