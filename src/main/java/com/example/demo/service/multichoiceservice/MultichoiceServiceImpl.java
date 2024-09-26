package com.example.demo.service.multichoiceservice;

import com.example.demo.dto.MultipleChoiceAnswerDTO;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.MultipleChoiceRepository;
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
}
