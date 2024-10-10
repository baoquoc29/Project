package com.example.demo.controller;

import com.example.demo.dto.ReadingAnswerDTO;
import com.example.demo.formatresponse.ApiResponse;
import com.example.demo.service.readingservice.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ReadingController {

    @Autowired
    private ReadingService readingService;


    @GetMapping("/reading/id/{id_quiz}/part/{part}")
    public ResponseEntity<ApiResponse> getReadingAnswerByQuizAndPart(
            @PathVariable("id_quiz") Long idQuiz,
            @PathVariable("part") String part) {

        List<ReadingAnswerDTO> answersDTOS = readingService.listReadingAnswersByPartAndQuiz(idQuiz, part);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(answersDTOS);
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setMessage("Reading " + part);
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/reading/idQuestion/{id_question}")
    public ResponseEntity<ApiResponse> getReadingAnswerById(
            @PathVariable("id_question") Long idQuestion) {
        ReadingAnswerDTO answersDTOS = readingService.getReadingAnswerById(idQuestion);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(answersDTOS);
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setMessage("Reading ");
        return ResponseEntity.ok(apiResponse);
    }
    @PutMapping("/reading/updated/idQuestion/{id_question}")
    public ResponseEntity<ApiResponse> updateReadingAnswerById(
            @PathVariable("id_question") Long idQuestion,@RequestBody ReadingAnswerDTO readingAnswerDTO) {
        ReadingAnswerDTO answersDTOS = readingService.updateReadingAnswer(idQuestion,readingAnswerDTO);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(answersDTOS);
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setMessage("Reading ");
        return ResponseEntity.ok(apiResponse);
    }

}
