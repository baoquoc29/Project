package com.example.demo.Service.AccountService;

import com.example.demo.DTO.AccountDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.DTO.UserDTODisplay;
import com.example.demo.DTO.UserResponDTO;
import com.example.demo.Entity.Account;
import com.example.demo.Entity.Customer;
import com.example.demo.Repository.AccountRepository;
import com.example.demo.Repository.CustomerRepository;
import com.example.demo.Repository.ListeningRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ListeningRepository listeningRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDTO signUp(UserDTO userDTO) {
        if(!checkEmail(userDTO.getEmail()) && !checkUsername(userDTO.getUsername())){
            Account user = modelMapper.map(userDTO,Account.class);
            Customer customer = modelMapper.map(userDTO,Customer.class);
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            Account savedAccount = userRepository.save(user);
            customer.setIduser(savedAccount.getId());
            Customer savedCustomer = customerRepository.save(customer);
            UserDTO savedUserDTO = modelMapper.map(savedCustomer, UserDTO.class);
            modelMapper.map(savedAccount, savedUserDTO);
            return savedUserDTO;
        }
        return null;
    }



    @Override
    public Account getAccount(String email) {
        return null;
    }
    @Override
    public boolean checkEmail(String email) {
        Account account = userRepository.findAccountByEmail(email);
        if (account == null) {
            return false;
        }
        return true;
    }


    @Override
    public boolean checkUsername(String username) {
        Account account = userRepository.findAccountByUsername(username);
        if (account == null) {
            return false;
        }
        return true;
    }
    @Override
    public UserResponDTO login(AccountDTO userDTO) {
        Account user = userRepository.findAccountByUsername(userDTO.getUsername());
        if (user != null && user.getUsername().equals(userDTO.getUsername())) {
            if (passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
                return modelMapper.map(user, UserResponDTO.class);
            }
        }
       return null;
    }

    @Override
    public List<UserResponDTO> getAllUserByRole(String role) {
        List<UserResponDTO> accounts = userRepository.findAllByRole(role);
        return accounts;
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        Account account = userRepository.findAccountByEmail(email);
        Long idUser = account.getId();
        Customer customer = customerRepository.findByIduser(idUser);
        UserDTO userDTO = modelMapper.map(customer, UserDTO.class);
        // chỉ modelMapper từ 1 object vào cái userDTO đã mapp
        modelMapper.map(account,userDTO);
        return userDTO;
    }

    @Override
    public UserDTODisplay getUserByEmailHidePassword(String email) {
        Account account = userRepository.findAccountByEmail(email);
        Long idUser = account.getId();
        Customer customer = customerRepository.findByIduser(idUser);
        UserDTODisplay userDTO = modelMapper.map(customer, UserDTODisplay.class);
        // chỉ modelMapper từ 1 object vào cái userDTO đã mapp
        modelMapper.map(account,userDTO);
        return userDTO;
    }


    @Override
    public UserResponDTO verified_Email(String email) {
        Account account = userRepository.findAccountByEmail(email);
        account.setAccuracy("verified");
        userRepository.save(account);
        UserResponDTO userResponDTO = modelMapper.map(account, UserResponDTO.class);
        return userResponDTO;
    }

}
