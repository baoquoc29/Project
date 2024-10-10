package com.example.demo.dto;

import lombok.Data;

@Data
public class ReadingContentDTO {
    private Long idReading;
    private String content;

    public ReadingContentDTO(String content, String part) {
        this.content = content;
        this.part = part;
    }

    private String part;
}
