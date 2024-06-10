package com.example.demo.Controller;

import com.example.demo.DTO.QuizDTO;
import com.example.demo.DTO.ReadingAnswerDTO;
import com.example.demo.FormatRespon.ResponObject;
import com.example.demo.Service.QuizService.QuizServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class QuizController {
    @Autowired
    QuizServiceImpl quizService;
    @GetMapping("/show_quiz")
    public ResponseEntity<ResponObject> showReading(){
        List<QuizDTO> list = quizService.getList();
        return ResponseEntity.ok(new ResponObject("Success","Quiz",list));
    }
}
