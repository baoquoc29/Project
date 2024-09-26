package com.example.demo.service.dictationservice;

import com.example.demo.dto.DictationQuestionDTO;
import com.example.demo.dto.DictationTopicDTO;
import com.example.demo.entity.DictationTopic;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.DictationQuestionRepository;
import com.example.demo.repository.DictationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DictationServiceImpl implements DictationService {
    @Autowired
    private DictationRepository dictationRepository;
    @Autowired
    private DictationQuestionRepository dictationQuestionRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<DictationTopicDTO> getDictation() {
        List<DictationTopic> list = dictationRepository.findAll();
        return list.stream()
                .map(dictationTopic -> modelMapper.map(dictationTopic, DictationTopicDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<DictationQuestionDTO> getDictationQuestion(Long idTopic) {
        List<DictationQuestionDTO> list = new ArrayList<>();
        list = dictationQuestionRepository.findAllByDictationTopic(idTopic);
        if (list == null) {
            throw new AppException(ErrorCode.NOT_FOUND_CUSTOMER);
        }
        return list;
    }
}
