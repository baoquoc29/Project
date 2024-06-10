package com.example.demo.Controller;

import com.example.demo.DTO.ListeningAnswersDTO;
import com.example.demo.FormatRespon.ResponObject;
import com.example.demo.Service.ListeningService.ListeningAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ListeningAnswerController {

    @Autowired
    private ListeningAnswerService listeningAnswerService;

    @GetMapping("/listening-answers")
    public ResponseEntity<ResponObject> getAllListeningAnswers() {
        List<ListeningAnswersDTO> answers = listeningAnswerService.getAllListeningAnswers();
        return ResponseEntity.ok(new ResponObject("True", "Success", answers));
    }

    @GetMapping("/listening/id/{id_quiz}/part/{part}")
    public ResponseEntity<ResponObject> getListeningAnswer(@PathVariable("id_quiz") Long idQuiz, @PathVariable("part") String part) {
        List<ListeningAnswersDTO> answersDTOS = listeningAnswerService.getAnyPartListeningAnswers(idQuiz, part);
        if (answersDTOS.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponObject("False", "Data not found", "Data is not found!"));
        }
        return ResponseEntity.ok(new ResponObject("True", "Success", answersDTOS));
    }
}
