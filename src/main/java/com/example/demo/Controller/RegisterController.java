package com.example.demo.Controller;

import com.example.demo.FormatRespon.ResponObject;
import com.example.demo.Model.Customer;
import com.example.demo.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class RegisterController {
    @Autowired
    CustomerRepository customerRepository;
    @PostMapping("/register")
    public ResponseEntity<?> insert(@RequestBody Customer customer){
        Optional<?> findCustomer = customerRepository.findByUsername(customer.getUsername());
        if(findCustomer.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponObject("False","The user is already in database",findCustomer));

        }
        else{
            customerRepository.save(customer);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponObject("True","Insert success",customer));
        }
    }
    @GetMapping("/get")
    public List<Customer> select (){
        return customerRepository.findAll();
    }
}
