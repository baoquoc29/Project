package com.example.demo.DTO;

import lombok.Data;

@Data
public class DictationQuestionDTO {
    private Long id;
    private Long id_topic;
    private String audio;
    private String pronunciation;
    private String nameTopic;

    public DictationQuestionDTO(Long id, Long id_topic, String audio, String pronunciation, String nameTopic) {
        this.id = id;
        this.id_topic = id_topic;
        this.audio = audio;
        this.pronunciation = pronunciation;
        this.nameTopic = nameTopic;
    }
}
