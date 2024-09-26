package com.example.demo.controller;

import com.example.demo.dto.ListeningAnswersDTO;
import com.example.demo.formatresponse.ApiResponse;
import com.example.demo.service.listeningservice.ListeningAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/listening")
public class ListeningAnswerController {

    @Autowired
    private ListeningAnswerService listeningAnswerService;

    @GetMapping("/id_quiz/{id_quiz}")
    public ResponseEntity<ApiResponse> getAllListeningAnswers(@PathVariable("id_quiz") Long idQuiz) {
        List<ListeningAnswersDTO> answers = listeningAnswerService.getAllListeningAnswers(idQuiz);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setMessage("Listening");
        apiResponse.setData(answers);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/id/{id_quiz}/part/{part}")
    public ResponseEntity<ApiResponse> getListeningAnswer(@PathVariable("id_quiz") Long idQuiz, @PathVariable("part") String part) {
        List<ListeningAnswersDTO> answersDTOS = listeningAnswerService.getAnyPartListeningAnswers(idQuiz, part);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setMessage("Listening " + part );
        apiResponse.setData(answersDTOS);
        return ResponseEntity.ok(apiResponse);
    }

}
