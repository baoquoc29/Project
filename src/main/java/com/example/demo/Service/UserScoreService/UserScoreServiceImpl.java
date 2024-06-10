package com.example.demo.Service.UserScoreService;

import com.example.demo.DTO.QuizDTO;
import com.example.demo.DTO.UserScoreDisplayDTO;
import com.example.demo.DTO.UserscoreDTO;
import com.example.demo.Entity.Quiz;
import com.example.demo.Entity.Userscore;
import com.example.demo.Repository.QuizRepository;
import com.example.demo.Repository.UserScoreRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserScoreServiceImpl implements UserScoreService {
    @Autowired
    UserScoreRepository userScoreRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    QuizRepository quizRepository;
    @Override
    public List<UserScoreDisplayDTO> ListScoreByIdUser(Long id) {
        return userScoreRepository.findAllSubmitTestById(id);
    }
    @Transactional
    @Override
    public UserscoreDTO saveScore(UserscoreDTO userscoreDTO) {
        Quiz quiz = quizRepository.findById(userscoreDTO.getIdQuiz()).orElse(null);
        quiz.setTotalComplete(quiz.getTotalComplete() + 1);
        quizRepository.save(quiz);
        // Map từ DTO sang entity
        Userscore userscore = modelMapper.map(userscoreDTO, Userscore.class);
        // Lưu entity vào cơ sở dữ liệu
        Userscore savedUserscore = userScoreRepository.save(userscore);
        // Map từ entity đã lưu trở lại DTO
        UserscoreDTO savedUserscoreDTO = modelMapper.map(savedUserscore, UserscoreDTO.class);
        // Trả về DTO đã lưu
        return savedUserscoreDTO;
    }
}
