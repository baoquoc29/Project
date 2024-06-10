package com.example.demo.Controller;

import com.example.demo.DTO.UserScoreDisplayDTO;
import com.example.demo.DTO.UserscoreDTO;
import com.example.demo.FormatRespon.ResponObject;
import com.example.demo.Service.UserScoreService.UserScoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class UserScoreController {
    @Autowired
    UserScoreServiceImpl userScoreService;

    @PostMapping("/complete_exam")
    public ResponseEntity<ResponObject> completeExam(@RequestBody UserscoreDTO userscoreDTO) {
        UserscoreDTO responDto = userScoreService.saveScore(userscoreDTO);
        if (responDto != null) {
            return ResponseEntity.ok(new ResponObject("Success", "Complete", responDto));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponObject("Fail", "Not found data", null));
    }

    @GetMapping("/get_exam/user_id/{id}")
    public ResponseEntity<ResponObject> getExamByUserId(@PathVariable("id") Long id) {
        List<UserScoreDisplayDTO> responDto = userScoreService.ListScoreByIdUser(id);
        if (responDto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponObject("Fail", "Not found data", null));
        }
        return ResponseEntity.ok(new ResponObject("Success", "Complete", responDto));
    }
}
