package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Table(name = "multiple_choice_questions")
@Data
public class MultipleChoiceQuestions {
    @Id
    @Column(name = "id_multiple_question")
    private Long idMultipleQuestion;

    @Column(name = "content")
    private String content;
    @Column(name = "part")
    private String part;

}
