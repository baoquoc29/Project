package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Entity
@Table(name = "user_accounts")
@Data
public class UserAccounts {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "membership")
    private String membership;
    @Column(name = "email")
    private String email;
    @Column(name = "accuracy")
    private String accuracy;
    @Column(name = "total_day_online")
    private int total_day_online;

    @Column(name = "check_day")
    private String check_day;

    @Column(name = "last_login")
    private LocalDate last_login;


}
