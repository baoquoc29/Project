package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
public class NotificationsDTO {
    private int id;
    private String titleNotification;
    private String messageNotification;
    private String typeNotification;
    private Long userId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private OffsetDateTime createdAt;
}
