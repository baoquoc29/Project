package com.example.demo.Repository;

import com.example.demo.DTO.ListeningAnswersDTO;
import com.example.demo.DTO.UserResponDTO;
import com.example.demo.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountByEmail(String email);
    Account findAccountByUsername(String username);
    @Query("SELECT new com.example.demo.DTO.UserResponDTO(ac.id, ct.idcustomer, ac.username, ac.membership, ac.email, ac.accuracy, ct.name, ct.age, ct.numberphone) " +
            "FROM Account ac, Customer ct " +
            "WHERE ac.id = ct.iduser AND ac.membership = :role")
    List<UserResponDTO> findAllByRole(@Param("role") String role);

}
