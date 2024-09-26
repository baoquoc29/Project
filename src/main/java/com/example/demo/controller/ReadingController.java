package com.example.demo.controller;

import com.example.demo.dto.ReadingAnswerDTO;
import com.example.demo.formatresponse.ApiResponse;
import com.example.demo.formatresponse.ResponObject;
import com.example.demo.service.readingservice.ReadingService;
import com.example.demo.service.readingservice.ReadingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
