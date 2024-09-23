package com.example.demo.Entity;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "reading_answers")
@Data
public class ReadingAnswers {
    @Id
    @Column(name = "id_answer")
    private Long idAnswer;

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

    @Column(name = "id_reading_content")
    private Long idReadingContent;

    @Column(name = "answer_description")
    private String answerDescription;

    @Column(name = "question")
    private String question;
    @Column(name ="id_quiz")
    private int id_quiz;
    @ManyToOne
    @JoinColumn(name = "id_quiz",insertable = false,updatable = false)
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "id_reading_content",insertable = false,updatable = false)
    private ReadingQuestions readingQuestions;

}
