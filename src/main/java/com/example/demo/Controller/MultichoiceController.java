package com.example.demo.Controller;

import com.example.demo.DTO.MultipleChoiceAnswerDTO;
import com.example.demo.FormatRespon.ResponObject;
import com.example.demo.Service.MultichoiceService.MultichoiceServiceImpl;
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
public class MultichoiceController {

    @Autowired
    private MultichoiceServiceImpl multichoiceService;

    @GetMapping("/show")
    public ResponseEntity<ResponObject> getMultichoice() {
        List<MultipleChoiceAnswerDTO> list = multichoiceService.listMultipleChoiceAnswers();
        return ResponseEntity.ok(new ResponObject("Success", "Part 5", list));
    }

    @GetMapping("/show/id/{id_quiz}")
    public ResponseEntity<ResponObject> getMultichoiceByQuiz(@PathVariable("id_quiz") Long idQuiz) {
        List<MultipleChoiceAnswerDTO> list = multichoiceService.listMultipleChoiceAnswersByQuizId(idQuiz);
        if (!list.isEmpty()) {
            return ResponseEntity.ok(new ResponObject("Success", "Part 5", list));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponObject("UnSuccess", "Part 5", "Not found"));
    }
}
