package com.example.demo.service.userscoreservice;

import com.example.demo.dto.UserScoreDisplayDTO;
import com.example.demo.dto.UserscoreDTO;
import com.example.demo.entity.Quiz;
import com.example.demo.entity.Userscore;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.QuizRepository;
import com.example.demo.repository.UserScoreRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserScoreServiceImpl implements UserScoreService {
    @Autowired
    UserScoreRepository userScoreRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public List<UserScoreDisplayDTO> ListScoreByIdUser(Long id) {
        return userScoreRepository.findAllSubmitTestById(id);
    }
    @Transactional
    @Override
    public UserscoreDTO saveScore(UserscoreDTO userscoreDTO) {
        if(quizRepository.findById(userscoreDTO.getIdQuiz()) == null){
            throw  new AppException(ErrorCode.NOT_FOUND_TOPIC);
        }
        Quiz quiz = quizRepository.findById(userscoreDTO.getIdQuiz()).orElseThrow();
        quiz.setTotalComplete(quiz.getTotalComplete() + 1);
        quizRepository.save(quiz);

        Userscore userscore = modelMapper.map(userscoreDTO, Userscore.class);

        Userscore savedUserscore = userScoreRepository.save(userscore);

        UserscoreDTO savedUserscoreDTO = modelMapper.map(savedUserscore, UserscoreDTO.class);


        return savedUserscoreDTO;
    }

    @Override
    public int getMaxUserScore(Long idCustomer) {
        if(customerRepository.findByIdcustomer(idCustomer) == null){
            throw  new AppException(ErrorCode.NOT_FOUND_CUSTOMER);
        }
        return userScoreRepository.findToMaxPoint(idCustomer).getScore();
    }

    @Override
    public int getTotalExamByCustomer(Long idCustomer) {
        if(customerRepository.findByIdcustomer(idCustomer) == null){
            throw  new AppException(ErrorCode.NOT_FOUND_CUSTOMER);
        }
        return userScoreRepository.findToTotalCompleteExam(idCustomer);
    }

    @Override
    public Page<UserScoreDisplayDTO> getListUserScoreByIdCustomer(Long idCustomer, Pageable pageable) {
        if(customerRepository.findByIdcustomer(idCustomer) == null){
            throw  new AppException(ErrorCode.NOT_FOUND_CUSTOMER);
        }
        return userScoreRepository.getListHistoryExam(idCustomer, pageable);
    }
}
