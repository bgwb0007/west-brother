package com.bgwb0007.intro.springboot.service;

import com.bgwb0007.intro.springboot.domain.resume.Education;
import com.bgwb0007.intro.springboot.domain.resume.EducationRepository;
import com.bgwb0007.intro.springboot.web.dto.EducationSaveRequestDto;
import com.bgwb0007.intro.springboot.web.dto.EducationUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EducationService {
    private final EducationRepository educationRepository;
    @Transactional
    public Long save(EducationSaveRequestDto requestDto){
        return educationRepository.save(new Education(requestDto)).getId();
    }
    public List<Map> findAllForHtml(){
        return educationRepository.findAllOrderByEndDateAsc().stream()
                .map(Education::toMapForList)
                .collect(Collectors.toList());
    }
    public Map findById(Long id){
        Education education = educationRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 Education 데이터가 없습니다. id="+id));
        return education.toMapForOne();
    }
    @Transactional
    public Long update(Long id, EducationUpdateRequestDto requestDto){
        Education education = educationRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 Education 데이터가 없습니다. id="+id));
        education.update(requestDto);
        return id;
    }
    @Transactional
    public Long delete(Long id){
        Education education = educationRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 Education 데이터가 없습니다. id="+id));
        educationRepository.delete(education);
        return id;
    }


}
