package com.example.demo.Repository;

import com.example.demo.DTO.ReadingAnswerDTO;
import com.example.demo.DTO.UserScoreDisplayDTO;
import com.example.demo.DTO.UserscoreDTO;
import com.example.demo.Entity.Userscore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserScoreRepository extends JpaRepository<Userscore,Long> {
    @Query("SELECT new com.example.demo.DTO.UserScoreDisplayDTO(uc.idScore,rc.id,rc.username,q.idQuiz,q.title,uc.score,uc.totalListening,uc.totalReading,uc.timeFinish,uc.dateFinish) " +
            "FROM Userscore uc " +
            "JOIN uc.account rc JOIN uc.quiz q ")
    List<UserScoreDisplayDTO> findAllSubmitTest();
    @Query("SELECT new com.example.demo.DTO.UserScoreDisplayDTO(uc.idScore,rc.id,rc.username,q.idQuiz,q.title,uc.score,uc.totalListening,uc.totalReading,uc.timeFinish,uc.dateFinish) " +
            "FROM Userscore uc " +
            "JOIN uc.account rc JOIN uc.quiz q WHERE  uc.idUser = :id ")
    List<UserScoreDisplayDTO> findAllSubmitTestById(@Param("id") Long id);

    @Query("SELECT new com.example.demo.DTO.UserScoreDisplayDTO(count(uc)) " +
            "FROM Userscore uc " +
            "JOIN uc.account rc JOIN uc.quiz q WHERE  q.idQuiz = :id ")
    int findTotalCompleteById(@Param("id") Long id);

}
