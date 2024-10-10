package com.example.demo.repository;

import com.example.demo.entity.ReadingQuestions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingQuestionRepository extends JpaRepository<ReadingQuestions,Long> {
}
