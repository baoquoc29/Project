package com.example.demo.repository;

import com.example.demo.dto.UserScoreDisplayDTO;
import com.example.demo.entity.Userscore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface UserScoreRepository extends JpaRepository<Userscore,Long> {
    @Query("SELECT new com.example.demo.dto.UserScoreDisplayDTO(uc.idCustomer,uc.idScore,rc.name,q.idQuiz,q.title,uc.score,uc.totalListening,uc.totalReading,uc.timeFinish,uc.dateFinish) " +
            "FROM Userscore uc " +
            "JOIN uc.customers rc JOIN uc.quiz q ")
    List<UserScoreDisplayDTO> findAllSubmitTest();
    @Query("SELECT new com.example.demo.dto.UserScoreDisplayDTO(uc.idCustomer,uc.idScore,rc.name,q.idQuiz,q.title,uc.score,uc.totalListening,uc.totalReading,uc.timeFinish,uc.dateFinish) " +
            "FROM Userscore uc " +
            "JOIN uc.customers rc JOIN uc.quiz q WHERE  uc.idCustomer = :id ")
    List<UserScoreDisplayDTO> findAllSubmitTestById(@Param("id") Long id);

    @Query("SELECT new com.example.demo.dto.UserScoreDisplayDTO(count(uc)) " +
            "FROM Userscore uc " +
            "JOIN uc.customers rc JOIN uc.quiz q WHERE  q.idQuiz = :id ")
    int findTotalCompleteById(@Param("id") Long id);
    @Query("SELECT new com.example.demo.dto.UserScoreDisplayDTO(uc.score) " +
            "FROM Userscore uc " +
            "WHERE uc.idCustomer = :idCustomer " +
            "AND uc.score = (SELECT MAX(u.score) FROM Userscore u WHERE u.idCustomer = :idCustomer)")
    UserScoreDisplayDTO findToMaxPoint(@Param("idCustomer") Long idCustomer);

    @Query("select  count(*) from Userscore  where idCustomer =:idCustomer  ")
    int findToTotalCompleteExam(@Param("idCustomer") Long idCustomer);

    @Query("SELECT new com.example.demo.dto.UserScoreDisplayDTO(uc.idCustomer,uc.idScore,rc.name,q.idQuiz,q.title,uc.score,uc.totalListening,uc.totalReading,uc.timeFinish,uc.dateFinish) " +
            "FROM Userscore uc " +
            "JOIN uc.customers rc JOIN uc.quiz q WHERE  uc.idCustomer = :idCustomer")
    Page<UserScoreDisplayDTO> getListHistoryExam(@Param("idCustomer") Long idCustomer,Pageable pageable);

}
