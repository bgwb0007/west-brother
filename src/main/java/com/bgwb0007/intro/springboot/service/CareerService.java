package com.bgwb0007.intro.springboot.service;

import com.bgwb0007.intro.springboot.domain.resume.CareerRepository;
import com.bgwb0007.intro.springboot.web.dto.CareerSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CareerService {
    private final CareerRepository careerRepository;
    @Transactional
    public Long save(CareerSaveRequestDto requestDto){
        return careerRepository.save(requestDto.toEntity()).getId();
    }
}
