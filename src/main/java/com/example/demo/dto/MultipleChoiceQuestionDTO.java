package com.example.demo.dto;

public class MultipleChoiceQuestionDTO {
    private Long idMultipleQuestion;
    private String content;

    public Long getIdMultipleQuestion() {
        return this.idMultipleQuestion;
    }

    public void setIdMultipleQuestion(Long idMultipleQuestion) {
        this.idMultipleQuestion = idMultipleQuestion;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
