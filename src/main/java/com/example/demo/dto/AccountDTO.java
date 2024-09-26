package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDate;

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
