package com.example.demo.service.readingservice;

import com.example.demo.dto.ReadingAnswerDTO;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ReadingServiceImpl implements ReadingService {
    @Autowired
    ReadingRepository repository;
    private String parts[] = new String[]{"Part 6", "Part 7"};

    @Override
    public List<ReadingAnswerDTO> listReadingAnswersByPartAndQuiz(Long id, String part) {
        if (repository.findById(id) == null) {
            throw new AppException(ErrorCode.NOT_FOUND_TOPIC);
        }
        if (!Arrays.asList(parts).contains(part)) {
            throw new AppException(ErrorCode.NOT_FOUND_PART);
        }
        return repository.findAllReadingAnswersWithContentByQuizAndPart(id, part);
    }

}
