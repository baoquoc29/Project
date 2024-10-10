package com.example.demo.controller;

import com.example.demo.dto.TotalAverageDTO;
import com.example.demo.dto.UserScoreDisplayDTO;
import com.example.demo.dto.UserscoreDTO;
import com.example.demo.formatresponse.ApiResponse;
import com.example.demo.service.userscoreservice.UserScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class UserScoreController {
    @Autowired
    UserScoreService userScoreService;

    @PostMapping("/complete_exam")
    public ResponseEntity<ApiResponse> completeExam(@RequestBody UserscoreDTO userscoreDTO) {
        UserscoreDTO responDto = userScoreService.saveScore(userscoreDTO);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Success");
        apiResponse.setData(responDto);
        apiResponse.setCode(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/get_exam/user_id/{id}")
    public ResponseEntity<ApiResponse> getExamByCustomerId(@PathVariable("id") Long id) {
        List<UserScoreDisplayDTO> responDto = userScoreService.ListScoreByIdUser(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Success");
        apiResponse.setData(responDto);
        apiResponse.setCode(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/get_max/id/{id_customer}")
    public ResponseEntity<ApiResponse> getMaxPointsByCustomerId(@PathVariable("id_customer") Long id) {
        int maxScore = userScoreService.getMaxUserScore(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Success");
        apiResponse.setData(maxScore);
        apiResponse.setCode(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/get_total/id/{id_customer}")
    public ResponseEntity<ApiResponse> getTotalExamCompleteByCustomer(@PathVariable("id_customer") Long id) {
        int totalExams = userScoreService.getTotalExamByCustomer(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Success");
        apiResponse.setData(totalExams);
        apiResponse.setCode(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/get_history/id/{id_customer}")
    public ResponseEntity<ApiResponse> getHistoryExamByCustomerID(
            @PathVariable("id_customer") Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Sort sort = Sort.by("score").descending();
        List<UserScoreDisplayDTO> content = userScoreService.getListUserScoreByIdCustomer(id, PageRequest.of(page, size, sort)).getContent();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Success");
        apiResponse.setData(content);
        apiResponse.setCode(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping()
    public ResponseEntity<ApiResponse> getExam() {
        List<TotalAverageDTO> responDto = userScoreService.getListAverage();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Success");
        apiResponse.setData(responDto);
        apiResponse.setCode(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }
}