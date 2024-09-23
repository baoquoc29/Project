package com.example.demo.Service.DictationService;

import com.example.demo.DTO.DictationQuestionDTO;
import com.example.demo.DTO.DictationTopicDTO;

import java.util.List;

public interface DictationService {
    public List<DictationTopicDTO> getDictation();
    public List<DictationQuestionDTO> getDictationQuestion(Long idTopic);
}
