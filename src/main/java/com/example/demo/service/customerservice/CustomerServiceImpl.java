package com.example.demo.service.customerservice;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.entity.Customers;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        Customers customers = new Customers();
        customers = modelMapper.map(customerDTO, Customers.class);
        return modelMapper.map(customerRepository.save(customers), CustomerDTO.class);
    }

    @Transactional
    public CustomerDTO update(Long idCustomer, CustomerDTO customerDTO) {
        // Kiểm tra số điện thoại hợp lệ nếu nó không null
        if (customerDTO.getNumberphone() != null && !isValidPhoneNumber(customerDTO.getNumberphone())) {
            throw new AppException(ErrorCode.INVALID_PHONE);
        }

        Customers customerToUpdate = customerRepository.findByIdcustomer(idCustomer);
        if (customerToUpdate == null) {
            throw new AppException(ErrorCode.NOT_FOUND_CUSTOMER);
        }

        if (customerDTO.getNumberphone() != null) {
            Customers existingCustomer = customerRepository.findCustomerByNumberphone(customerDTO.getNumberphone());
            if (existingCustomer != null && !existingCustomer.getIdcustomer().equals(idCustomer)) {
                throw new AppException(ErrorCode.DATA_EXISTS);
            }
        }

        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(customerDTO, customerToUpdate);

        Customers updatedCustomers = customerRepository.save(customerToUpdate);

        return modelMapper.map(updatedCustomers, CustomerDTO.class);
    }



    private boolean isValidPhoneNumber(String numberPhone) {
        return numberPhone != null && numberPhone.matches("\\d{10}");
    }

    @Override
    public CustomerDTO findOne(Long id) {
        Customers customers = customerRepository.findByIdcustomer(id);
        if (customers == null) {
            throw new AppException(ErrorCode.NOT_FOUND_CUSTOMER);
        }

        CustomerDTO savedCustomer = modelMapper.map(customers, CustomerDTO.class);
        return savedCustomer;
    }

    @Override
    public CustomerDTO getCustomer(Long id) {
        Customers customers = customerRepository.findByIdcustomer(id);
        if (customers == null) {
            return null;
        }

        CustomerDTO savedCustomer = modelMapper.map(customers, CustomerDTO.class);
        return savedCustomer;
    }
}
