package com.example.demo.Service.CustomerService;

import com.example.demo.DTO.CustomerDTO;

public interface CustomerService {
    CustomerDTO save(CustomerDTO customerDTO);
    CustomerDTO update(CustomerDTO customerDTO);
    CustomerDTO findOne(Long id);
}
