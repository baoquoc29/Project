package com.example.demo.controller;

import com.example.demo.dto.QuizDTO;
import com.example.demo.dto.UserScoreDisplayDTO;
import com.example.demo.formatresponse.ApiResponse;
import com.example.demo.service.quizservice.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/show")
    public ResponseEntity<ApiResponse> showPageableQuiz(@RequestParam("size") int size,@RequestParam("page") int page ) {
        List<QuizDTO> list = quizService.getAllListByPagable(PageRequest.of(page,size));
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Quiz");
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setData(list);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/id")
    public ResponseEntity<ApiResponse> showQuizById(@RequestParam("idQuiz") Long id) {
        QuizDTO quizDTO = quizService.getByIdQuiz(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Quiz");
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setData(quizDTO);
        return ResponseEntity.ok(apiResponse);
    }
    @PutMapping("/id")
    public ResponseEntity<ApiResponse> updateQuiz(@RequestParam("idQuiz") Long idQuiz,@RequestBody QuizDTO quizDTO) {
        QuizDTO updated = quizService.updateByIdQuiz(quizDTO,idQuiz);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Quiz");
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setData(updated);
        return ResponseEntity.ok(apiResponse);
    }
}
