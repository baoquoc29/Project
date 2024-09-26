package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Entity
@Table(name = "customers")
@Data
@Validated
public class Customers {
    @Id
    @Column(name = "idcustomer")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcustomer;

    @Column(name = "iduser")
    private Long iduser;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Long age;

    @Column(name = "numberphone")
    private String numberphone;
    @OneToOne
    @JoinColumn(name = "iduser",updatable = false,insertable = false)
    private UserAccounts userAccounts;
}
