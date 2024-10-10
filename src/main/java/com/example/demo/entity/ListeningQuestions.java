package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "listening_questions")
@Data
public class ListeningQuestions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_content")
    private Long idContent;

    @Column(name = "image")
    private String image;

    @Column(name = "audio")
    private String audio;

    @Column(name = "part_listening")
    private String partListening;

}
