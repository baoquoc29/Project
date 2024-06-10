package com.example.demo.DTO;

import lombok.Data;

@Data
public class ReadingAnswerDTO {
    private Long idAnswer;
    private Long idReadingContent;
    private Long id_quiz;
    private String title;
    private String question;
    private String iamge;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answerCorrect;
    private String answerDescription;
    private String part;

    public ReadingAnswerDTO(Long idAnswer, Long idReadingContent,Long id_quiz,String title, String optionA, String optionB, String optionC, String optionD, String answerCorrect, String answerDescription, String question,String iamge,String part) {
        this.idAnswer = idAnswer;
        this.idReadingContent = idReadingContent;
        this.id_quiz = id_quiz;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.answerCorrect = answerCorrect;
        this.question = question;
        this.title = title;
        this.iamge = iamge;
        this.answerDescription = answerDescription;
        this.part = part;

    }

}
