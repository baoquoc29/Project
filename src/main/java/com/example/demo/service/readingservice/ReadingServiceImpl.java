package com.example.demo.service.readingservice;

import com.example.demo.dto.ReadingAnswerDTO;
import com.example.demo.entity.ReadingAnswers;
import com.example.demo.entity.ReadingQuestions;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.ReadingQuestionRepository;
import com.example.demo.repository.ReadingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ReadingServiceImpl implements ReadingService {
    @Autowired
    ReadingRepository repository;
    private String parts[] = new String[]{"Part 6", "Part 7"};
    @Autowired
    ReadingQuestionRepository questionRepository;
    @Autowired
    ModelMapper modelMapper;
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

    @Override
    public ReadingAnswerDTO getReadingAnswerById(Long id) {
        return repository.findAllReadingAnswersWithContentById(id);
    }

    @Override
    public ReadingAnswerDTO updateReadingAnswer(Long id, ReadingAnswerDTO readingAnswerDTO) {
        ReadingAnswerDTO readingAnswerDTO1 = repository.findAllReadingAnswersWithContentById(id);
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(readingAnswerDTO, readingAnswerDTO1);
        ReadingAnswers readingAnswers = repository.save(modelMapper.map(readingAnswerDTO1, ReadingAnswers.class));
        ReadingQuestions readingQuestions = questionRepository.save(modelMapper.map(readingAnswerDTO1, ReadingQuestions.class));

        modelMapper.map(readingAnswers,readingAnswerDTO1);
        modelMapper.map(readingQuestions,readingAnswerDTO1);
        return readingAnswerDTO1;
    }

}
