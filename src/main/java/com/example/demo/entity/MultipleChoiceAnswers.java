package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "multiple_choice_answers")
@Data
public class MultipleChoiceAnswers {
    @Id
    @Column(name = "id_answer")
    private Long idAnswer;

    @Column(name = "id_question")
    private Long idQuestion;

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

    @Column(name = "answer_description")
    private String answerDescription;

    @Column(name = "id_quiz")
    private Long idQuiz;
    @ManyToOne
    @JoinColumn(name = "id_quiz",insertable = false,updatable = false)
    private Quiz quiz;
    @OneToOne
    @JoinColumn(name = "id_question",insertable = false,updatable = false)
    private MultipleChoiceQuestions question;

}
