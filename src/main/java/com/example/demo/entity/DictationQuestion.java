package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "dictation_questions")
public class DictationQuestion {
    @Id
    @Column(name = "id_dictation_content")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_dictation_topic")
    private Long topicId;
    @Column(name = "audio")
    private String audio;
    @Column(name = "pronunciation")
    private String pronunciation;

    @ManyToOne
    @JoinColumn(name = "id_dictation_topic", insertable = false,updatable = false)
    private DictationTopic dictationTopic;
}