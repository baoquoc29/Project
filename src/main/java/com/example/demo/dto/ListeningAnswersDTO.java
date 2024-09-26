package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ListeningAnswersDTO {
    private Long idAnswer;
    private Long idContent;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answerCorrect;
    private String question;
    private String image;
    private String audio;
    private String partListening;
    private Long idQuiz;
    private String title;
    public ListeningAnswersDTO(Long idAnswer, Long idContent,Long idQuiz,String title, String optionA, String optionB, String optionC, String optionD, String answerCorrect, String question, String image, String audio, String partListening) {
        this.idAnswer = idAnswer;
        this.idContent = idContent;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.answerCorrect = answerCorrect;
        this.question = question;
        this.image = image;
        this.audio = audio;
        this.partListening = partListening;
        this.idQuiz = idQuiz;
        this.title = title;

    }
}