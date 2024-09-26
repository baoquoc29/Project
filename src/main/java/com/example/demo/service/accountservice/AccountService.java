package com.example.demo.service.accountservice;

import com.example.demo.dto.*;
import com.example.demo.entity.UserAccounts;

import java.util.List;

public interface AccountService {
    UserDTO signUp(UserDTO userDTO);

    UserAccounts getAccount(String email);

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
}
