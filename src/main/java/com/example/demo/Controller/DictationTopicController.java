package com.example.demo.Controller;

import com.example.demo.DTO.DictationQuestionDTO;
import com.example.demo.DTO.DictationTopicDTO;
import com.example.demo.FormatRespon.ResponObject;
import com.example.demo.Service.DictationService.DictationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DictationTopicController {
    @Autowired
    private DictationService dictationService;

    @GetMapping("/dictation/get_dictation_topic")
    public ResponseEntity<ResponObject> getDictationTopic() {
        List<DictationTopicDTO> dictationTopicDTO = dictationService.getDictation();
        if(dictationTopicDTO.size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponObject("False", "Data not found", "Data is not found!"));
        }
        else{
            return ResponseEntity.ok(new ResponObject("Success", "Data", dictationTopicDTO));
        }
    }
    @GetMapping("/dictation/id/{id_dictation}")
    public ResponseEntity<ResponObject> getDictationQuestionByIdTopic(@PathVariable("id_dictation") Long id) {
        List<DictationQuestionDTO> dictationTopicDTO = dictationService.getDictationQuestion(id);
        if(dictationTopicDTO.size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponObject("False", "Data not found", "Data is not found!"));
        }
        else{
            return ResponseEntity.ok(new ResponObject("Success", "Data", dictationTopicDTO));
        }
    }
}
