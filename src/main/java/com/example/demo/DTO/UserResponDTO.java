package com.example.demo.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

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
    public UserResponDTO(Long id,Long idcustomer,String username,String membership,String email,String accuracy,String name,Long age,String numberphone){
        this.id = id;
        this.idcustomer = idcustomer;
        this.username = username;
        this.membership = membership;
        this.email = email;
        this.accuracy = accuracy;
        this.name = name;
        this.age = age;
        this.numberphone = numberphone;

    }
}
