package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "account")
@Data
public class Account {
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

}
