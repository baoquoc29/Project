package com.example.demo.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "listening_questions")
@Data
public class ListeningQuestions {
    @Id
    @Column(name = "id_content")
    private Long idContent;

    @Column(name = "image")
    private String image;

    @Column(name = "audio")
    private String audio;

    @Column(name = "part_listening")
    private String partListening;

}
