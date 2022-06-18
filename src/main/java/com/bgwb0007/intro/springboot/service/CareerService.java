package com.bgwb0007.intro.springboot.service;

import com.bgwb0007.intro.springboot.domain.resume.Career;
import com.bgwb0007.intro.springboot.domain.resume.CareerRepository;
import com.bgwb0007.intro.springboot.web.dto.CareerListResponseDto;
import com.bgwb0007.intro.springboot.web.dto.CareerSaveRequestDto;
import com.bgwb0007.intro.springboot.web.dto.CareerUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CareerService {
    private final CareerRepository careerRepository;
    @Transactional
    public Long save(CareerSaveRequestDto requestDto) {
        return careerRepository.save(new Career(requestDto)).getId();
    }
    public List<Map> findAllForHtml(){
        return careerRepository.findAllOrderByEndDateAsc().stream()
                .map(Career::toMapForList)
                .collect(Collectors.toList());
    }
    
    public Map findById(Long id){
        Career career = careerRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 Career 데이터가 없습니다. id="+id));
        return career.toMapForOne();
    }
    @Transactional
    public Long update(Long id, CareerUpdateRequestDto requestDto){
        Career career = careerRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 career 데이터가 없습니다. id="+id));
        career.update(requestDto);
        return id;
    }
    @Transactional
    public Long delete(Long id){
        Career career = careerRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 career 데이터가 없습니다. id="+id));
        careerRepository.delete(career);
        return id;
    }

}
