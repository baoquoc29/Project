package com.example.demo.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AccountDTO {
    private Long id;
    private String username;
    private String password;
    private String membership;
    private String email;
    private int total_day_online;
    private LocalDate last_login;

}
