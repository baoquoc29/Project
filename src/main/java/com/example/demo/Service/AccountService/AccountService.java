package com.example.demo.Service.AccountService;

import com.example.demo.DTO.*;
import com.example.demo.Entity.UserAccounts;

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
     String putCheckDayOnline(String name,String date);
     String getCheckDayOnline(String name);
     void clearCheckDayOnline();
}
