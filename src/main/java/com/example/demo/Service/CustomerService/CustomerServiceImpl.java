package com.example.demo.Service.CustomerService;

import com.example.demo.DTO.CustomerDTO;
import com.example.demo.Entity.Customer;
import com.example.demo.Repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer = modelMapper.map(customerDTO, Customer.class);
        return modelMapper.map(customerRepository.save(customer), CustomerDTO.class);
    }

    @Transactional
    public CustomerDTO update(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        modelMapper.map(customerDTO, customer);
        Customer updatedCustomer = customerRepository.save(customer);
        return modelMapper.map(updatedCustomer, CustomerDTO.class);
    }


    @Override
    public CustomerDTO findOne(Long id) {
        Customer customer = customerRepository.findByIduser(id);
        if (customer == null) {
            return null;
        }
        CustomerDTO savedCustomer = modelMapper.map(customer,CustomerDTO.class);
        return savedCustomer;
    }
}
