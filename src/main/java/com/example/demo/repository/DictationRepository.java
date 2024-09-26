package com.example.demo.repository;

import com.example.demo.entity.DictationTopic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DictationRepository extends JpaRepository<DictationTopic,Long> {

}
