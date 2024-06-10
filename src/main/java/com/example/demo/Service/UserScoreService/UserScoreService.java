package com.example.demo.Service.UserScoreService;

import com.example.demo.DTO.UserScoreDisplayDTO;
import com.example.demo.DTO.UserscoreDTO;

import java.util.List;

public interface UserScoreService {
    List<UserScoreDisplayDTO> ListScoreByIdUser(Long id);
    UserscoreDTO saveScore(UserscoreDTO userscoreDTO);

}
