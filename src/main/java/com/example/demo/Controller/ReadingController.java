package com.example.demo.Controller;

import com.example.demo.DTO.ReadingAnswerDTO;
import com.example.demo.FormatRespon.ResponObject;
import com.example.demo.Service.ReadingService.ReadingServiceImpl;
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
    private ReadingServiceImpl readingService;

    @GetMapping("/show_reading")
    public ResponseEntity<ResponObject> showReading() {
        List<ReadingAnswerDTO> list = readingService.listReadingAnswers();
        return ResponseEntity.ok(new ResponObject("Success", "Reading", list));
    }

    @GetMapping("/reading/id/{id_quiz}/part/{part}")
    public ResponseEntity<ResponObject> getReadingAnswerByQuizAndPart(@PathVariable("id_quiz") Long idQuiz, @PathVariable("part") String part) {
        List<ReadingAnswerDTO> answersDTOS = readingService.listReadingAnswersByPartAndQuiz(idQuiz, part);
        if (answersDTOS.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponObject("False", "Data not found", "Data is not found!"));
        }
        return ResponseEntity.ok(new ResponObject("True", "Success", answersDTOS));
    }
}
