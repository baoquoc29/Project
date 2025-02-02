package com.example.demo.service.accountservice;

import com.example.demo.dto.AccountDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserDTODisplay;
import com.example.demo.dto.UserResponDTO;
import com.example.demo.entity.UserAccounts;
import com.example.demo.entity.Customers;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ListeningRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.stream;

@Service
public class AccountServiceImpl implements AccountService {
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
        // Kiểm tra thông tin không được để trống
        if (userDTO.getUsername() == null || userDTO.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Tên đăng nhập không được để trống.");
        }
        if (userDTO.getNumberphone() == null || userDTO.getNumberphone().trim().isEmpty()) {
            throw new IllegalArgumentException("Số điện thoại không được để trống.");
        }
        if (userDTO.getEmail() == null || userDTO.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email không được để trống.");
        }
        if (userDTO.getPassword() == null || userDTO.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Mật khẩu không được để trống.");
        }
        if (userDTO.getAge() == null) {
            throw new IllegalArgumentException("Tuổi không được để trống.");
        }
        if (userDTO.getName() == null) {
            throw new IllegalArgumentException("Vui lòng không được để trống.");
        }
        // Kiểm tra tính duy nhất của email, tên đăng nhập và số điện thoại
        if (checkEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("Email đã tồn tại.");
        }
        if (checkUsername(userDTO.getUsername())) {
            throw new IllegalArgumentException("Username đã tồn tại.");
        }
        if (checkNumberPhone(userDTO.getNumberphone())) {
            throw new IllegalArgumentException("Số điện thoại đã tồn tại.");
        }

        // Mã hóa mật khẩu
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());

        // Tạo đối tượng UserAccounts
        UserAccounts user = modelMapper.map(userDTO, UserAccounts.class);
        user.setPassword(encodedPassword);
        UserAccounts savedUserAccounts = userRepository.save(user);

        // Lưu thông tin khách hàng
        Customers customers = modelMapper.map(userDTO, Customers.class);
        customers.setIduser(savedUserAccounts.getId());
        customerRepository.save(customers);

        // Chuyển đổi và trả về đối tượng UserDTO đã lưu
        UserDTO savedUserDTO = modelMapper.map(customers, UserDTO.class);
        modelMapper.map(savedUserAccounts, savedUserDTO);
        return savedUserDTO;
    }


    @Override
    public List<UserResponDTO> listOfPage(Pageable pageable) {
        List<UserResponDTO> list = userRepository.findAllByPage(pageable).getContent();
        return list;
    }


    @Override
    public boolean checkEmail(String email) {
        UserAccounts userAccounts = userRepository.findAccountByEmail(email);
        return userAccounts != null;
    }

    public boolean checkNumberPhone(String number) {
        Customers customers = customerRepository.findCustomerByNumberphone(number);
        return customers != null;
    }


    @Override
    public boolean checkUsername(String username) {
        UserAccounts userAccounts = userRepository.findAccountByUsername(username);
        if (userAccounts == null) {
            return false;
        }
        return true;
    }

    @Override
    public UserResponDTO login(AccountDTO userDTO) {
        if(userDTO.getUsername() == null || userDTO.getPassword() == null){
            throw  new AppException(ErrorCode.NOT_NULL);
        }
        UserAccounts user = userRepository.findAccountByUsername(userDTO.getUsername());
        if (user == null) {
            throw new AppException(ErrorCode.NOT_FOUND_CUSTOMER);
        }

        Customers customers = customerRepository.findByIduser(user.getId());
        if (customers == null) {
            throw new AppException(ErrorCode.NOT_FOUND_CUSTOMER);
        }

        // So sánh username
        if (user.getUsername().equals(userDTO.getUsername())) {
            // So sánh mật khẩu
            if (passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {

                user.setLast_login(userDTO.getLast_login());

                userRepository.save(user);

                UserResponDTO userResponseDTO = new UserResponDTO();

                modelMapper.map(user, userResponseDTO);

                modelMapper.map(customers, userResponseDTO);

                return userResponseDTO;
            } else {

                throw new AppException(ErrorCode.INVALID_PASSWORD);
            }
        }

        throw new RuntimeException();
    }


    @Override
    public List<UserResponDTO> getAllUserByRole(String role) {
        List<UserResponDTO> accounts = userRepository.findAllByRole(role);
        if (accounts.isEmpty()) {
            throw new AppException(ErrorCode.NOT_FOUND_CUSTOMER);
        }
        return accounts;
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        UserAccounts userAccounts = userRepository.findAccountByEmail(email);
        Long idUser = userAccounts.getId();
        Customers customers = customerRepository.findByIduser(idUser);
        UserDTO userDTO = modelMapper.map(customers, UserDTO.class);
        // chỉ modelMapper từ 1 object vào cái userDTO đã mapp
        modelMapper.map(userAccounts, userDTO);
        return userDTO;
    }

    @Override
    public UserDTODisplay getUserByEmailHidePassword(String email) {
        UserAccounts userAccounts = userRepository.findAccountByEmail(email);
        if (userAccounts == null) {
            throw new AppException(ErrorCode.INVALID_EMAIL);
        }
        Long idUser = userAccounts.getId();
        Customers customers = customerRepository.findByIduser(idUser);
        UserDTODisplay userDTO = modelMapper.map(customers, UserDTODisplay.class);
        modelMapper.map(userAccounts, userDTO);
        return userDTO;
    }


    @Override
    public UserResponDTO verified_Email(String email) {
        UserAccounts userAccounts = userRepository.findAccountByEmail(email);
        userAccounts.setAccuracy("verified");
        userRepository.save(userAccounts);
        UserResponDTO userResponDTO = modelMapper.map(userAccounts, UserResponDTO.class);
        return userResponDTO;
    }

    @Override
    public void updateTotalDayOnline(String name) {
        if (userRepository.findAccountByUsername(name) == null) {
            throw new AppException(ErrorCode.NOT_FOUND_CUSTOMER);
        }
        userRepository.updateTotalDayOnline(name);
    }

    @Override
    public int getTotalDayOnline(String name) {
        if (userRepository.findAccountByUsername(name) == null) {
            throw new AppException(ErrorCode.NOT_FOUND_CUSTOMER);
        }
        return userRepository.findTotalDayOnline(name);
    }

    @Override
    public String putCheckDayOnline(String name, String date) {
        if (userRepository.findAccountByUsername(name) == null) {
            throw new AppException(ErrorCode.NOT_FOUND_CUSTOMER);
        }
        userRepository.updateCheckDayOnlineOfWeek(name, date);
        return date;

    }

    @Override
    public String getCheckDayOnline(String name) {
        if (userRepository.findAccountByUsername(name) == null) {
            throw new AppException(ErrorCode.NOT_FOUND_CUSTOMER);
        }
        return userRepository.findCheckDayOnline(name);
    }

    @Override
    @Scheduled(cron = "0 0 0 * * MON") // Chạy vào lúc 00:00 mỗi thứ hai
    @Transactional
    public void clearCheckDayOnline() {
        userRepository.clearCheckDayColumn(LocalDate.now().minusWeeks(1));
    }

    @Override
    public void deleteAccountById(Long id) {

        Customers customer = customerRepository.findByIduser(id);

        if (customer != null) {
            customerRepository.delete(customer);
        }

        userRepository.deleteById(id);
    }

    @Override
    public List<UserResponDTO> getAllUser() {
        List<UserResponDTO> list = userRepository.getAllAccounts();
        return list;
    }

}
