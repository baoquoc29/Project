package com.example.demo.repository;

import com.example.demo.entity.MultipleChoiceQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MultipleChoiceQuestionRepository extends JpaRepository<MultipleChoiceQuestions,Long> {
}
