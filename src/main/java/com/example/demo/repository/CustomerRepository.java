package com.example.demo.repository;

import com.example.demo.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Long> {
    Customers findByIduser(Long idUser);
    Customers findCustomerByNumberphone(String number);
    Customers findByIdcustomer(Long idCustomer);
}
