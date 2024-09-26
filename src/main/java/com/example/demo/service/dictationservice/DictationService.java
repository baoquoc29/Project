package com.example.demo.service.dictationservice;

import com.example.demo.dto.DictationQuestionDTO;
import com.example.demo.dto.DictationTopicDTO;

import java.util.List;

public interface DictationService {
    public List<DictationTopicDTO> getDictation();

    public List<DictationQuestionDTO> getDictationQuestion(Long idTopic);
}
