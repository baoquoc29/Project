package com.example.demo.DTO;

import com.example.demo.Entity.Account;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class CustomerDTO {
    private Long idcustomer;
    private Long iduser;
    private String name;
    private Long age;
    private String numberphone;

}
