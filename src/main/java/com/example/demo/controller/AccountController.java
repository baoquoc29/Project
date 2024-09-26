package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.emailconfig.Email;
import com.example.demo.emailconfig.EmailService;
import com.example.demo.formatresponse.ApiResponse;
import com.example.demo.service.accountservice.AccountService;
import com.example.demo.service.customerservice.CustomerServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/accounts")
public class AccountController {
    @Autowired
    EmailService emailService;

    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerServiceImpl customerService;
    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signUp(@RequestBody UserDTO userDTO) {
        UserDTO responDTO = accountService.signUp(userDTO);
        Email email = new Email(userDTO.getEmail(), "Xác nhận thông tin đăng ký tài khoản", "Xác nhận");
        emailService.sendEmail(email);
        ApiResponse<UserDTO> apiResponse = new ApiResponse<>();
        apiResponse.setData(responDTO);
        apiResponse.setCode(HttpStatus.CREATED.value());
        apiResponse.setMessage("Success");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody AccountDTO accountDTO) {
        accountDTO.setLast_login(LocalDate.now());
        UserResponDTO responDTO = accountService.login(accountDTO);
        ApiResponse<UserResponDTO> apiResponse = new ApiResponse<>();
        apiResponse.setData(responDTO);
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setMessage("Success");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/forgot")
    public ResponseEntity<ApiResponse> forgot(@RequestBody SendForgotPassDTO passDTO) {
        String recipientEmail = passDTO.getEmail();
        String subject = "Thông báo";
        String content = "Mật khẩu của bạn là";
        Email emailValue = new Email(recipientEmail, content, subject);
        emailService.sendForgotEmail(emailValue);
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setMessage("Email is sended");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/get_user/role/{role}")
    public ResponseEntity<ApiResponse> getUserByRole(@PathVariable("role") String role) {
        List<UserResponDTO> list = accountService.getAllUserByRole(role);
        ApiResponse<List<UserResponDTO>> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Success");
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setData(list);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/get_user/email/{email}")
    public ResponseEntity<ApiResponse> getInformationUserByEmail(@PathVariable("email") String email) {
        UserDTODisplay data = accountService.getUserByEmailHidePassword(email);
        ApiResponse<UserDTODisplay> apiResponse = new ApiResponse<>();
        apiResponse.setData(data);
        apiResponse.setMessage("Success");
        apiResponse.setCode(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }


    @PutMapping("/update/customer/id/{id_customer}")
    public ResponseEntity<ApiResponse> updateCustomer(@PathVariable("id_customer") Long id, @RequestBody CustomerDTO customerDTO) {
        CustomerDTO existingCustomerDTO = customerService.findOne(id);
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(customerDTO, existingCustomerDTO);
        CustomerDTO updatedCustomerDTO = customerService.update(existingCustomerDTO);
        ApiResponse<CustomerDTO> apiResponse = new ApiResponse<>();
        apiResponse.setData(updatedCustomerDTO);
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setMessage("Updated success");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/get_user/customer/id/{id_user}")
    public ResponseEntity<ApiResponse> getIdCustomerByUser(@PathVariable("id_user") Long id) {
        CustomerDTO exCustomerDTO = customerService.findOne(id);
        ApiResponse<CustomerDTO> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Success");
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setData(exCustomerDTO);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/get_customer/customer/id/{id_customer}")
    public ResponseEntity<ApiResponse> getCustomerById(@PathVariable("id_customer") Long id) {
        CustomerDTO exCustomerDTO = customerService.getCustomer(id);
        ApiResponse<CustomerDTO> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Success");
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setData(exCustomerDTO);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/update_day_online/account/username/{user_name}")
    public ResponseEntity<ApiResponse> update_day_online(@PathVariable("user_name") String username) {
        accountService.updateTotalDayOnline(username);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setMessage("Update check day success");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/get_total_day_online/account/username/{user_name}")
    public ResponseEntity<ApiResponse> get_day_onlineByUsername(@PathVariable("user_name") String username) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Success");
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setData(accountService.getTotalDayOnline(username));
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/update_check_day/account/username/{user_name}")
    public ResponseEntity<ApiResponse> update_day_online_week(@PathVariable("user_name") String username, @RequestBody String date) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Success");
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setData(accountService.putCheckDayOnline(username, date));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/get_check_day/account/username/{user_name}")
    public ResponseEntity<ApiResponse> update_day_online_week(@PathVariable("user_name") String username) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Success");
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setData(accountService.getCheckDayOnline(username));
        return ResponseEntity.ok(apiResponse);

    }
}
