package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "quiz")
@Data
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quiz_seq")
    @SequenceGenerator(name = "quiz_seq", sequenceName = "quiz_id_quiz_seq", allocationSize = 1)
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
