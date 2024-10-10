package com.example.demo.controller;

import com.example.demo.dto.NotificationsDTO;
import com.example.demo.formatresponse.ApiResponse;
import com.example.demo.service.notificationservice.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @PostMapping
    public ResponseEntity<ApiResponse> postNotifcation(@RequestBody NotificationsDTO notification) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(notificationService.saveNotification(notification));
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setMessage("Notification saved");
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping
    public ResponseEntity<ApiResponse> getNotifications(@RequestParam long id,@RequestParam String type) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(notificationService.findByIdOrType(id,type));
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setMessage("Notification found");
        return ResponseEntity.ok(apiResponse);
    }
    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteNotification(@RequestParam int id) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(notificationService.deleteNotification(id));
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setMessage("Notification deleted");
        return ResponseEntity.ok(apiResponse);
    }
}
