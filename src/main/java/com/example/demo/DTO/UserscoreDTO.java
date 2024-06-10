package com.example.demo.DTO;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

@Data
public class UserscoreDTO {
    private Long idScore;
    private Long idUser;
    private Long idQuiz;
    private int score;
    private String timeFinish;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateFinish;

    private Long totalListening;
    private Long totalReading;

    // Constructor không tham số
    public UserscoreDTO() {
    }

    // Constructor có tham số
    public UserscoreDTO(Long idScore, Long idUser, Long idQuiz, int score, Long totalListening, Long totalReading) {
        this.idScore = idScore;
        this.idUser = idUser;
        this.idQuiz = idQuiz;
        this.score = score;
        this.totalListening = totalListening;
        this.totalReading = totalReading;
    }
}
