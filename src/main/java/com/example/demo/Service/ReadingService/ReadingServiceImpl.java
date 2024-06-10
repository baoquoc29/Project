package com.example.demo.Service.ReadingService;

import com.example.demo.DTO.MultipleChoiceAnswerDTO;
import com.example.demo.DTO.ReadingAnswerDTO;
import com.example.demo.Repository.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadingServiceImpl  implements ReadingService {
    @Autowired
    ReadingRepository repository;
    @Override
    public List<ReadingAnswerDTO> listReadingAnswers() {
        return repository.findAllReadingAnswersWithContent();
    }

    @Override
    public List<ReadingAnswerDTO> listReadingAnswersByPartAndQuiz(Long id, String part) {
        return repository.findAllReadingAnswersWithContentByQuizAndPart(id,part);
    }

}
