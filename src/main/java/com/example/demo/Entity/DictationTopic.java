package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "dictation_topic")
public class DictationTopic {
    @Id
    @Column(name = "id_dictation_topic")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name_dictation")
    private String name;
    @Column(name = "total_questions")
    private int totalQuestions;
    @Column(name = "difficulty")
    private String difficulty;
}
