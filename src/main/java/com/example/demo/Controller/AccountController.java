package com.example.demo.Controller;

import com.example.demo.DTO.*;
import com.example.demo.EmailConfig.Email;
import com.example.demo.EmailConfig.EmailService;
import com.example.demo.FormatRespon.ResponObject;
import com.example.demo.Service.AccountService.AccountService;
import com.example.demo.Service.CustomerService.CustomerServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v1/auth")
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
    public ResponseEntity<ResponObject> signUp(@RequestBody UserDTO userDTO) {
        UserDTO responDTO = accountService.signUp(userDTO);
        if (responDTO != null) {
            Email email = new Email(userDTO.getEmail(),"Xác nhận thông tin đăng ký tài khoản","Xác nhận");
            emailService.sendEmail(email);
            return ResponseEntity.ok(new ResponObject("Success", "Sign Up Success", responDTO));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponObject("UnSuccess", "Fail Sign Up", "Email already exists"));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponObject> login(@RequestBody AccountDTO accountDTO) {
        UserResponDTO responDTO = accountService.login(accountDTO);
        if (responDTO != null   && responDTO.getAccuracy() != null ) {
            return ResponseEntity.ok(new ResponObject("Success", "Login Success", responDTO));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponObject("UnSuccess", "Fail Login", "Password is not correct or Email is not verified"));
    }
    @PostMapping("/forgot")
    public ResponseEntity<?> forgot(@RequestBody SendForgotPassDTO passDTO) {
        try {
            String recipientEmail = passDTO.getEmail();

            if (!isValidEmail(recipientEmail)) {
                return ResponseEntity.badRequest().body("Invalid email format" + recipientEmail);
            }

            // Use default values for subject and content
            String subject = "Thông báo";
            String content = "Mật khẩu của bạn là";

            Email emailValue = new Email(recipientEmail, content, subject);


            emailService.sendForgotEmail(emailValue);

            return ResponseEntity.ok("Email sent");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending email");
        }
    }

    @GetMapping("/get_user/role/{role}")
    public ResponseEntity<ResponObject> getUserByRole(@PathVariable("role") String role) {
        List<UserResponDTO> list = accountService.getAllUserByRole(role);
        if (list != null && !list.isEmpty()) {
            return ResponseEntity.ok(new ResponObject("Success", "Get All Success", list));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponObject("UnSuccess", "Not found any member", ""));
    }

    @GetMapping("/get_user/email/{email}")
    public ResponseEntity<ResponObject> getInformationUserByEmail(@PathVariable("email") String email) {
        UserDTODisplay data = accountService.getUserByEmailHidePassword(email);
        if (data != null && data.getIduser() != null) {
            return ResponseEntity.ok(new ResponObject("Success", "Get User Success", data));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponObject("UnSuccess", "Not found any member", ""));
    }


    @GetMapping("/comfirm/email/{email}")
    public ResponseEntity<ResponObject> verified_Email(@PathVariable("email") String email) {
        UserResponDTO userResponDTO = accountService.verified_Email(email);
        if (userResponDTO != null) {
            return ResponseEntity.ok(new ResponObject("Success", "Verified success", "Đã xác nhận thành công email: " + userResponDTO.getEmail()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponObject("UnSuccess", "Not found any member", ""));
    }


    @PutMapping("/update/customer/id/{id_user}")
    public ResponseEntity<ResponObject> updateCustomer(@PathVariable("id_user") Long id, @RequestBody CustomerDTO customerDTO) {
        CustomerDTO existingCustomerDTO = customerService.findOne(id);
        if (existingCustomerDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponObject("UnSuccess", "Không tìm thấy khách hàng", ""));
        }
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(customerDTO, existingCustomerDTO);
        CustomerDTO updatedCustomerDTO = customerService.update(existingCustomerDTO);

        return ResponseEntity.ok(new ResponObject("Success", "Cập nhật thành công", updatedCustomerDTO));
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
