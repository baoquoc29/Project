package com.example.demo.controller;

import com.example.demo.dto.DictationQuestionDTO;
import com.example.demo.dto.DictationTopicDTO;
import com.example.demo.formatresponse.ApiResponse;
import com.example.demo.service.dictationservice.DictationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dictation")
public class DictationTopicController {
    @Autowired
    private DictationService dictationService;

    @GetMapping("/get_dictation_topic")
    public ResponseEntity<ApiResponse> getDictationTopic() {
        List<DictationTopicDTO> dictationTopicDTO = dictationService.getDictation();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(dictationTopicDTO);
        apiResponse.setMessage("Success");
        apiResponse.setCode(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/id/{id_dictation}")
    public ResponseEntity<ApiResponse> getDictationQuestionByIdTopic(@PathVariable("id_dictation") Long id) {
        List<DictationQuestionDTO> dictationQuestion = dictationService.getDictationQuestion(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(dictationQuestion);
        apiResponse.setMessage("Success");
        apiResponse.setCode(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }
}
