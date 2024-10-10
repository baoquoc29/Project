package com.example.demo.repository;

import com.example.demo.entity.Customers;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Long> {
    Customers findByIduser(Long idUser);
    Customers findCustomerByNumberphone(String number);
    Customers findByIdcustomer(Long idCustomer);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM customers u WHERE u.iduser = :idUser",nativeQuery = true)
    void deleteById(@Param("idUser") long idUser);
}
