package com.bgwb0007.intro.springboot.service;

import com.bgwb0007.intro.springboot.domain.shoes.Shoes;
import com.bgwb0007.intro.springboot.domain.shoes.ShoesRepository;
import com.bgwb0007.intro.springboot.web.dto.ShoesListResponseDto;
import com.bgwb0007.intro.springboot.web.dto.ShoesSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoesService {

    private final ShoesRepository shoesRepository;

    public Long save(ShoesSaveRequestDto requestDto) throws IOException {
        return shoesRepository.save(requestDto.saveFilesAndToEntity()).getId();
    }
    public List<ShoesListResponseDto> findAll(){
        return shoesRepository.findAll().stream()
                .map(ShoesListResponseDto::new)
                .collect(Collectors.toList());
    }
}
