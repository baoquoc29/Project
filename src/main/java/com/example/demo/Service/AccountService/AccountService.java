package com.example.demo.Service.AccountService;

import com.example.demo.DTO.*;
import com.example.demo.Entity.Account;

import java.util.List;

public interface AccountService {
     UserDTO signUp(UserDTO userDTO);
     Account getAccount(String email);
     boolean checkEmail(String email);
     boolean checkUsername(String username);
     UserResponDTO login(AccountDTO accountDTO);
     List<UserResponDTO> getAllUserByRole(String role);
     UserDTO getUserByEmail(String email);
     UserDTODisplay getUserByEmailHidePassword(String email);
     UserResponDTO verified_Email(String email);

}
