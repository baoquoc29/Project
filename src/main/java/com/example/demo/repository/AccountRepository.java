package com.example.demo.repository;

import com.example.demo.dto.UserResponDTO;
import com.example.demo.entity.UserAccounts;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<UserAccounts, Long> {
    UserAccounts findAccountByEmail(String email);

    UserAccounts findAccountByUsername(String username);

    @Query("SELECT new com.example.demo.dto.UserResponDTO(ac.id, ct.idcustomer, ac.username, ac.membership, ac.email, ac.accuracy, ct.name, ct.age, ct.numberphone,ac.last_login) " +
            "FROM UserAccounts ac, Customers ct " +
            "WHERE ac.id = ct.iduser AND ac.membership = :role")
    List<UserResponDTO> findAllByRole(@Param("role") String role);

    @Query("SELECT new com.example.demo.dto.UserResponDTO(ac.id, ct.idcustomer, ac.username, ac.membership, ac.email, ac.accuracy, ct.name, ct.age, ct.numberphone,ac.last_login) " +
            "FROM UserAccounts ac, Customers ct " +
            "WHERE ac.id = ct.iduser")
    List<UserResponDTO> getAllAccounts();

    @Modifying
    @Transactional
    @Query("update UserAccounts ac set ac.total_day_online = ac.total_day_online + 1 where ac.username = :name")
    void updateTotalDayOnline(@Param("name") String name);


    @Query("select ac.total_day_online from UserAccounts as ac where ac.username = :username")
    int findTotalDayOnline(@Param("username") String username);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user_accounts SET check_day = CASE WHEN check_day IS NULL THEN :date ELSE CONCAT(check_day, ',', :date) END WHERE username = :name", nativeQuery = true)
    void updateCheckDayOnlineOfWeek(@Param("name") String name, @Param("date") String date);

    @Query("select ac.check_day from UserAccounts as ac where ac.username = :username")
    String findCheckDayOnline(@Param("username") String username);


    @Modifying
    @Transactional
    @Query(value = "UPDATE user_accounts e SET e.checkDay = NULL WHERE e.date < :date", nativeQuery = true)
    void clearCheckDayColumn(@Param("date") LocalDate date);

    @Query("SELECT new com.example.demo.dto.UserResponDTO(ac.id, ct.idcustomer, ac.username, ac.membership, ac.email, ac.accuracy, ct.name, ct.age, ct.numberphone,ac.last_login) " +
            "FROM UserAccounts ac, Customers ct WHERE ac.id = ct.iduser")
    Page<UserResponDTO> findAllByPage(Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user_accounts u WHERE u.id = :idUser",nativeQuery = true)
    void deleteById(@Param("idUser") long idUser);

}
