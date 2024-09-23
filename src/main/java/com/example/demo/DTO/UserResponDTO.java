package com.example.demo.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class UserResponDTO {
    private Long id;
    private Long idcustomer;
    private String username;
    private String membership;
    private String email;
    private String accuracy;
    private String name;
    private Long age;
    private String numberphone;
    private int total_day_online;
    private LocalDate last_login;
    public UserResponDTO(Long id,Long idcustomer,String username,String membership,String email,String accuracy,String name,Long age,String numberphone,LocalDate last_login){
        this.id = id;
        this.idcustomer = idcustomer;
        this.username = username;
        this.membership = membership;
        this.email = email;
        this.accuracy = accuracy;
        this.name = name;
        this.age = age;
        this.last_login = last_login;
        this.numberphone = numberphone;

    }
    public UserResponDTO(int total_day_online) {
        this.total_day_online = total_day_online;
    }
}
