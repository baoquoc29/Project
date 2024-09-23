package com.example.demo.Service.DictationService;

import com.example.demo.DTO.DictationQuestionDTO;
import com.example.demo.DTO.DictationTopicDTO;
import com.example.demo.Entity.DictationQuestion;
import com.example.demo.Entity.DictationTopic;
import com.example.demo.Repository.DictationQuestionRepository;
import com.example.demo.Repository.DictationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        List<DictationTopic> list = new ArrayList<>();
        list =  dictationRepository.findAll();
        List<DictationTopicDTO> listDto = new ArrayList<>();
        for(DictationTopic dictationTopic : list){
            listDto.add(modelMapper.map(dictationTopic,DictationTopicDTO.class));
        }
        return listDto;
    }

    @Override
    public List<DictationQuestionDTO> getDictationQuestion(Long idTopic) {
        List<DictationQuestionDTO> list = new ArrayList<>();
        list =  dictationQuestionRepository.findAllByDictationTopic(idTopic);
        return list;
    }
}
