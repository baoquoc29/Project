package com.example.demo.service.accountservice;

import com.example.demo.dto.*;
import com.example.demo.entity.UserAccounts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountService {
    UserDTO signUp(UserDTO userDTO);


    List<UserResponDTO> listOfPage(Pageable pageable);

    boolean checkEmail(String email);

    boolean checkUsername(String username);

    UserResponDTO login(AccountDTO accountDTO);

    List<UserResponDTO> getAllUserByRole(String role);

    UserDTO getUserByEmail(String email);

    UserDTODisplay getUserByEmailHidePassword(String email);

    UserResponDTO verified_Email(String email);

    void updateTotalDayOnline(String name);

    int getTotalDayOnline(String name);

    String putCheckDayOnline(String name, String date);

    String getCheckDayOnline(String name);

    void clearCheckDayOnline();

    void deleteAccountById(Long id);

    List<UserResponDTO> getAllUser();
}
