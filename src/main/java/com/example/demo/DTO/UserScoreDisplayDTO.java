package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
@Data
public class UserScoreDisplayDTO {
    private Long idScore;
    private Long idUser;
    private String username;
    private Long idQuiz;
    private String title;
    private int score;
    private String timeFinish;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateFinish;
    private Long totalListening;
    private Long totalReading;
    public UserScoreDisplayDTO(Long idScore,Long idUser,String username,Long idQuiz,String title,int score,Long totalListening,Long totalReading,String timeFinish,LocalDate dateFinish) {
        this.idScore = idScore;
        this.idUser = idUser;
        this.username = username;
        this.idQuiz = idQuiz;
        this.title = title;
        this.score = score;
        this.totalListening = totalListening;
        this.totalReading = totalReading;
        this.timeFinish = timeFinish;
        this.dateFinish =dateFinish;
    }
    public UserScoreDisplayDTO(Long a ){

    }
}
