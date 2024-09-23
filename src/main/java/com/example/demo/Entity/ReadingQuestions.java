package com.example.demo.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "reading_questions")
@Data
public class ReadingQuestions {
    @Id
    @Column(name = "id_reading")
    private Long idReading;

    @Column(name = "content")
    private String content;

    @Column(name = "part_reading")
    private String part;

}
