package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "multiple_choice_questions")
@Data
public class MultipleChoiceQuestions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_multiple_question")
    private Long idMultipleQuestion;

    @Column(name = "content")
    private String content;
    @Column(name = "part")
    private String part;

}
