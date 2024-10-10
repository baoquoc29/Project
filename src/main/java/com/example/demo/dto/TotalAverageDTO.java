package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class TotalAverageDTO {
    private Long idQuiz;
    private int month;
    private int count;
    private double avgPoints;
    private int year;
}
