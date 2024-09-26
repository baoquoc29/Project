package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "userscore")
@Data
public class Userscore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_score")
    private Long idScore;

    @Column(name = "id_customer")
    private Long idCustomer;

    @Column(name = "id_quiz")
    private Long idQuiz;

    @Column(name = "total_score")
    private int score;

    @Column(name = "time_finish")
    private String timeFinish;

    @Column(name = "date_finish")
    private LocalDate dateFinish;

    @Column(name = "total_listening")
    private Long totalListening;

    @Column(name = "total_reading")
    private Long totalReading;

    @ManyToOne
    @JoinColumn(name = "id_customer", insertable = false, updatable = false)
    private Customers customers;
    @OneToOne
    @JoinColumn(name = "id_quiz",insertable = false, updatable = false)
    private Quiz quiz;

}
