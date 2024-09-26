package com.example.demo.controller;

import com.example.demo.dto.QuizDTO;
import com.example.demo.formatresponse.ApiResponse;
import com.example.demo.formatresponse.ResponObject;
import com.example.demo.service.quizservice.QuizService;
import com.example.demo.service.quizservice.QuizServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @GetMapping("/show_quiz")
    public ResponseEntity<ApiResponse> showReading() {
        List<QuizDTO> list = quizService.getList();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Quiz");
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setData(list);
        return ResponseEntity.ok(apiResponse);
    }
}
