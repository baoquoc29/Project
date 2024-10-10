package com.example.demo.dto;

import lombok.Data;

@Data
public class ListeningContentDTO {
    public ListeningContentDTO( String image, String audio, String partListening) {
        this.image = image;
        this.audio = audio;
        this.partListening = partListening;
    }

    private Long idContent;
    private String image;
    private String audio;
    private String partListening;
}
