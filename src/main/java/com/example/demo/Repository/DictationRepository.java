package com.example.demo.Repository;

import com.example.demo.Entity.DictationTopic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DictationRepository extends JpaRepository<DictationTopic,Long> {

}
