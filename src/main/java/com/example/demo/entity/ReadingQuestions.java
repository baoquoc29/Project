package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "reading_questions")
@Data
public class ReadingQuestions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reading")
    private Long idReading;

    @Column(name = "content")
    private String content;

    @Column(name = "part_reading")
    private String part;

}
