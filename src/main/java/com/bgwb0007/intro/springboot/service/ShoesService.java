package com.bgwb0007.intro.springboot.service;

import com.bgwb0007.intro.springboot.domain.shoes.ShoesRepository;
import com.bgwb0007.intro.springboot.web.dto.ShoesSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ShoesService {

    private final ShoesRepository shoesRepository;

    public Long save(ShoesSaveRequestDto requestDto) throws IOException {
        return shoesRepository.save(requestDto.toEntityAndSaveFiles()).getId();
    }
}
