package com.example.demo.DTO;

import lombok.Data;

@Data
public class MultipleChoiceAnswerDTO {
    private Long idAnswer;
    private Long idQuiz;
    private Long idQuestion;
    private String question;
    private String title;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answerCorrect;
    private String answerDescription;
    private String part;
    public MultipleChoiceAnswerDTO(Long idAnswer,Long idQuiz,Long idQuestion,String question,String title,String optionA,String optionB,String optionC,String optionD,String answerCorrect,String answerDescription,String part) {
        this.idAnswer = idAnswer;
        this.idQuiz = idQuiz;
        this.idQuestion = idQuestion;
        this.question = question;
        this.title = title;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.answerCorrect = answerCorrect;
        this.answerDescription = answerDescription;
        this.part = part;

    }
}
