package com.example.demo.dto;

import lombok.Data;

@Data
public class ReadingAnswerDTO {
    private Long idAnswer;
    private Long idReadingContent;
    private Long idQuiz;
    private String title;
    private String question;
    private String image;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answerCorrect;
    private String answerDescription;
    private String part;

    public ReadingAnswerDTO() {
    }

    public ReadingAnswerDTO(Long idAnswer, Long idReadingContent, Long idQuiz, String title, String optionA, String optionB, String optionC, String optionD, String answerCorrect, String answerDescription, String question, String image, String part) {
        this.idAnswer = idAnswer;
        this.idReadingContent = idReadingContent;
        this.idQuiz = idQuiz;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.answerCorrect = answerCorrect;
        this.question = question;
        this.title = title;
        this.image = image;
        this.answerDescription = answerDescription;
        this.part = part;

    }

}
