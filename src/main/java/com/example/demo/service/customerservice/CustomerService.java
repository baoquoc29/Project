package com.example.demo.service.customerservice;

import com.example.demo.dto.CustomerDTO;

public interface CustomerService {
    CustomerDTO save(CustomerDTO customerDTO);

    CustomerDTO update(CustomerDTO customerDTO);

    CustomerDTO findOne(Long id);

    CustomerDTO getCustomer(Long id);
}
