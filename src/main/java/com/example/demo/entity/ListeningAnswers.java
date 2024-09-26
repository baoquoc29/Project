package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "listening_answers")
public class ListeningAnswers {
    @Id
    @Column(name = "id_answer")
    private Long idAnswer;

    @Column(name = "id_content")
    private Long idContent;
    @Column(name ="id_quiz")
    private int id_quiz;
    @Column(name = "option_a")
    private String optionA;

    @Column(name = "option_b")
    private String optionB;

    @Column(name = "option_c")
    private String optionC;

    @Column(name = "option_d")
    private String optionD;

    @Column(name = "answer_correct")
    private String answerCorrect;

    @Column(name = "question")
    private String question;
    @ManyToOne
    @JoinColumn(name = "id_content", insertable = false, updatable = false)
    private ListeningQuestions listeningQuestions;
    @ManyToOne
    @JoinColumn(name = "id_quiz",insertable = false,updatable = false)
    private Quiz quiz;

}
