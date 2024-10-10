package com.example.demo.controller;

import com.example.demo.dto.MultipleChoiceAnswerDTO;
import com.example.demo.formatresponse.ApiResponse;
import com.example.demo.service.multichoiceservice.MultichoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class MultichoiceController {

    @Autowired
    private MultichoiceService multichoiceService;

    @GetMapping("/show")
    public ResponseEntity<ApiResponse> getMultichoice() {
        List<MultipleChoiceAnswerDTO> list = multichoiceService.listMultipleChoiceAnswers();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setData(list);
        apiResponse.setMessage("Part 5 ");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/show/id/{id_quiz}")
    public ResponseEntity<ApiResponse> getMultichoiceByQuiz(@PathVariable("id_quiz") Long idQuiz) {
        List<MultipleChoiceAnswerDTO> list = multichoiceService.listMultipleChoiceAnswersByQuizId(idQuiz);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setData(list);
        apiResponse.setMessage("Part 5 ");
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/get/idQuestion/{id_question}")
    public ResponseEntity<ApiResponse> get(@PathVariable("id_question") Long id_question) {
        MultipleChoiceAnswerDTO updated = multichoiceService.getMultipleChoice(id_question);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setData(updated);
        apiResponse.setMessage("Part 5 ");
        return ResponseEntity.ok(apiResponse);
    }
    @PutMapping("/updated/idQuestion/{id_question}")
    public ResponseEntity<ApiResponse> updated(@PathVariable("id_question") Long id_question , @RequestBody MultipleChoiceAnswerDTO multipleChoiceAnswerDTO) {
        MultipleChoiceAnswerDTO updated = multichoiceService.updateMultipleChoice(id_question,multipleChoiceAnswerDTO);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setData(updated);
        apiResponse.setMessage("Part 5 ");
        return ResponseEntity.ok(apiResponse);
    }
}
