package com.example.demo.DTO;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class DictationTopicDTO {
    private Long id;
    private String name;
    private int totalQuestions;
    private String difficulty;
}
