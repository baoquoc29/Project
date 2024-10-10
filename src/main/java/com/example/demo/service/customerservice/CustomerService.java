package com.example.demo.service.customerservice;

import com.example.demo.dto.CustomerDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerService {
    CustomerDTO save(CustomerDTO customerDTO);

    CustomerDTO update(Long id,CustomerDTO customerDTO);

    CustomerDTO findOne(Long id);

    CustomerDTO getCustomer(Long id);

}
