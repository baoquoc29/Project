package com.example.demo.dto;

import lombok.Data;

import java.util.Date;
@Data
public class QuizDTO {
    private Long idQuiz;
    private String title;
    private String description;
    private Date createdAt;
    private String difficulty;
    private int totalComplete;
}
