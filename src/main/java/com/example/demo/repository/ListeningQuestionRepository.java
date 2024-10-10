package com.example.demo.repository;

import com.example.demo.entity.ListeningQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListeningQuestionRepository extends JpaRepository<ListeningQuestions,Long> {

}
