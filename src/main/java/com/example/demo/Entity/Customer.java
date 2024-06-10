package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "customer")
@Data
public class Customer {
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
    private Account account;
}
