package com.example.demo.service.multichoiceservice;

import com.example.demo.dto.MultipleChoiceAnswerDTO;
import com.example.demo.entity.MultipleChoiceAnswers;
import com.example.demo.entity.MultipleChoiceQuestions;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.MultipleChoiceQuestionRepository;
import com.example.demo.repository.MultipleChoiceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MultichoiceServiceImpl implements MultichoiceService {
    @Autowired
    MultipleChoiceRepository multipleChoiceRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    MultipleChoiceQuestionRepository questionRepository;

    @Override
    public List<MultipleChoiceAnswerDTO> listMultipleChoiceAnswers() {
        List<MultipleChoiceAnswerDTO> list = multipleChoiceRepository.findMultipleChoiceAnswer();
        return list.stream()
                .map(listChoice -> modelMapper.map(listChoice, MultipleChoiceAnswerDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<MultipleChoiceAnswerDTO> listMultipleChoiceAnswersByQuizId(Long quizId) {
        if(multipleChoiceRepository.findById(quizId) == null){
            throw new AppException(ErrorCode.NOT_FOUND_TOPIC);
        }
        return multipleChoiceRepository.findMultipleChoiceAnswerByQuiz(quizId);
    }

    @Override
    public MultipleChoiceAnswerDTO updateMultipleChoice(Long answerId, MultipleChoiceAnswerDTO multipleChoiceAnswerDTO) {
        MultipleChoiceAnswerDTO existingAnswerDTO = multipleChoiceRepository.findMultipleChoiceAnswerByQuizAndIdAnswer(answerId);
        if (existingAnswerDTO == null) {
            throw new EntityNotFoundException("Multiple choice answer not found.");
        }


        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(multipleChoiceAnswerDTO, existingAnswerDTO);

        MultipleChoiceAnswers multipleChoiceAnswers = modelMapper.map(existingAnswerDTO, MultipleChoiceAnswers.class);
        MultipleChoiceQuestions multipleChoiceQuestions = modelMapper.map(existingAnswerDTO, MultipleChoiceQuestions.class);


        MultipleChoiceAnswers updatedAnswers = multipleChoiceRepository.save(multipleChoiceAnswers);
        MultipleChoiceQuestions updatedQuestions = questionRepository.save(multipleChoiceQuestions);


        modelMapper.map(updatedAnswers, existingAnswerDTO);
        modelMapper.map(updatedQuestions, existingAnswerDTO);
        return existingAnswerDTO;
    }

    @Override
    public MultipleChoiceAnswerDTO getMultipleChoice(Long answerId) {
        MultipleChoiceAnswerDTO existingAnswerDTO = multipleChoiceRepository.findMultipleChoiceAnswerByQuizAndIdAnswer(answerId);
        return existingAnswerDTO;
    }


}
