package com.example.demo.controller;

import com.example.demo.dto.ListeningAnswersDTO;
import com.example.demo.formatresponse.ResponObject;
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
    public ResponseEntity<ResponObject> getAllListeningAnswers(@PathVariable("id_quiz") Long idQuiz) {
        List<ListeningAnswersDTO> answers = listeningAnswerService.getAllListeningAnswers(idQuiz);
        return ResponseEntity.ok(new ResponObject("True", "Success", answers));
    }

    @GetMapping("/id/{id_quiz}/part/{part}")
    public ResponseEntity<ResponObject> getListeningAnswer(@PathVariable("id_quiz") Long idQuiz, @PathVariable("part") String part) {
        List<ListeningAnswersDTO> answersDTOS = listeningAnswerService.getAnyPartListeningAnswers(idQuiz, part);
        if (answersDTOS.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponObject("False", "Data not found", "Data is not found!"));
        }
        return ResponseEntity.ok(new ResponObject("True", "Success", answersDTOS));
    }

}
