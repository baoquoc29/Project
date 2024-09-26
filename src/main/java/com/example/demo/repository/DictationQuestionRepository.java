package com.example.demo.repository;

import com.example.demo.dto.DictationQuestionDTO;
import com.example.demo.entity.DictationQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DictationQuestionRepository extends JpaRepository<DictationQuestion, Long> {
    @Query("SELECT new com.example.demo.dto.DictationQuestionDTO(dq.id, dq.topicId, dq.audio, dq.pronunciation, dt.name) " +
            "FROM DictationQuestion dq JOIN DictationTopic dt ON dq.topicId = dt.id WHERE dq.topicId = :id_dictation_topic")
    public List<DictationQuestionDTO> findAllByDictationTopic(@Param("id_dictation_topic") Long id_dictation_topic);


}
