package com.example.demo.dto;

import lombok.Data;

@Data
public class MultipleChoiceQuestionDTO {
    private Long idMultipleQuestion;
    private String content;

    public MultipleChoiceQuestionDTO(String content, String part) {
        this.content = content;
        this.part = part;
    }

    private String part;
}
