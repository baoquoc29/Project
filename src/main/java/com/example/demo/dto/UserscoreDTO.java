package com.example.demo.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

@Data
public class UserscoreDTO {
    private Long idScore;
    private Long idCustomer;
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
}
