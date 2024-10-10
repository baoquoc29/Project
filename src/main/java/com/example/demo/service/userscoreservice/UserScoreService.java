package com.example.demo.service.userscoreservice;

import com.example.demo.dto.TotalAverageDTO;
import com.example.demo.dto.UserScoreDisplayDTO;
import com.example.demo.dto.UserscoreDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserScoreService {
    List<UserScoreDisplayDTO> ListScoreByIdUser(Long id);

    UserscoreDTO saveScore(UserscoreDTO userscoreDTO);

    int getMaxUserScore(Long idCustomer);

    int getTotalExamByCustomer(Long idCustomer);

    Page<UserScoreDisplayDTO> getListUserScoreByIdCustomer(Long idCustomer, Pageable pageable);

    List<TotalAverageDTO> getListAverage();
}
