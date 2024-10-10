package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
@Data
public class UserScoreDisplayDTO {
    private Long idScore;
    private Long idCustomer;
    private String username;
    private Long idQuiz;
    private String title;
    private int score;
    private String timeFinish;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateFinish;
    private Long totalListening;
    private Long totalReading;
    private double avgPoints;
    private int month;
    private int year;
    private Long count;
    public UserScoreDisplayDTO(Long idCustomer,Long idScore,String username,Long idQuiz,String title,int score,Long totalListening,Long totalReading,String timeFinish,LocalDate dateFinish) {
        this.idScore = idScore;
        this.username = username;
        this.idQuiz = idQuiz;
        this.idCustomer = idCustomer;
        this.title = title;
        this.score = score;
        this.totalListening = totalListening;
        this.totalReading = totalReading;
        this.timeFinish = timeFinish;
        this.dateFinish =dateFinish;
    }
    public UserScoreDisplayDTO(Long a ){

    }
    public UserScoreDisplayDTO(int score) {
        this.score = score;
    }
    public UserScoreDisplayDTO(Long idQuiz,double avgPoints,Long count,int month,int year) {
        this.avgPoints = avgPoints;
        this.idQuiz = idQuiz;
        this.month = month;
        this.year = year;
        this.count =count;
    }
}
