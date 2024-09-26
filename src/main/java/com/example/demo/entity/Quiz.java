package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "quiz")
@Data
public class Quiz {
    @Id
    @Column(name = "id_quiz")
    private Long idQuiz;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "difficulty")
    private String difficulty;
    @Column(name = "total_complete")
    private int totalComplete;

}
