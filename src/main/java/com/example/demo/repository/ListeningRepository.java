package com.example.demo.repository;

import com.example.demo.dto.ListeningAnswersDTO;
import com.example.demo.entity.ListeningAnswers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListeningRepository extends JpaRepository<ListeningAnswers, Long> {

    @Query("SELECT new com.example.demo.dto.ListeningAnswersDTO(la.idAnswer, la.idContent,q.idQuiz,q.title, la.optionA, la.optionB, la.optionC, la.optionD, la.answerCorrect, la.question, lc.image, lc.audio, lc.partListening,la.descriptionAnswer) " +
            "FROM ListeningAnswers la " +
            "JOIN la.listeningQuestions lc JOIN la.quiz q WHERE q.idQuiz = :id_quiz ORDER BY la.idAnswer")
    List<ListeningAnswersDTO> findAllListeningAnswersWithContent(@Param("id_quiz") Long id_quiz);

    @Query("SELECT new com.example.demo.dto.ListeningAnswersDTO(la.idAnswer, la.listeningQuestions.idContent, q.idQuiz, q.title, la.optionA, la.optionB, la.optionC, la.optionD, la.answerCorrect, la.question, la.listeningQuestions.image, la.listeningQuestions.audio, la.listeningQuestions.partListening,la.descriptionAnswer) " +
            "FROM ListeningAnswers la " +
            "JOIN la.listeningQuestions lc " +
            "JOIN la.quiz q " +
            "WHERE q.idQuiz = :id_quiz AND lc.partListening = :part_listening ORDER BY la.idAnswer")
    List<ListeningAnswersDTO> findAnyPartListeningAnswersWithContent(@Param("id_quiz") Long idQuiz, @Param("part_listening") String partListening);

    @Query("SELECT new com.example.demo.dto.ListeningAnswersDTO(la.idAnswer, la.listeningQuestions.idContent, q.idQuiz, q.title, la.optionA, la.optionB, la.optionC, la.optionD, la.answerCorrect, la.question, la.listeningQuestions.image, la.listeningQuestions.audio, la.listeningQuestions.partListening,la.descriptionAnswer) " +
            "FROM ListeningAnswers la " +
            "JOIN la.listeningQuestions lc " +
            "JOIN la.quiz q " +
            "WHERE q.idQuiz = :id_quiz AND lc.partListening = :part_listening ORDER BY la.idAnswer")
    Page<ListeningAnswersDTO> findAnyPageWithPageable(@Param("id_quiz") Long idQuiz, @Param("part_listening") String partListening, Pageable pageable);

    @Query("SELECT new com.example.demo.dto.ListeningAnswersDTO(la.idAnswer, la.listeningQuestions.idContent, q.idQuiz, q.title, la.optionA, la.optionB, la.optionC, la.optionD, la.answerCorrect, la.question, la.listeningQuestions.image, la.listeningQuestions.audio, la.listeningQuestions.partListening,la.descriptionAnswer) " +
            "FROM ListeningAnswers la " +
            "JOIN la.listeningQuestions lc " +
            "JOIN la.quiz q " +
            "WHERE la.idAnswer =:id ORDER BY la.idAnswer")
    ListeningAnswersDTO findAnyPartListeningAnswersById(@Param("id") Long id);
}

