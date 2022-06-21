package com.bgwb0007.intro.springboot.web;

import com.bgwb0007.intro.springboot.service.EducationService;
import com.bgwb0007.intro.springboot.web.dto.EducationSaveRequestDto;
import com.bgwb0007.intro.springboot.web.dto.EducationUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class EducationApiController {
    private final EducationService educationService;

    @PostMapping("/api/v1/education")
    public Long save(@RequestBody EducationSaveRequestDto requestDto){
        return educationService.save(requestDto);
    }
    @GetMapping("/api/v1/education")
    public List<Map> findAllForHtml(){
        return educationService.findAllForHtml();
    }
    @GetMapping("/api/v1/career/{id}")
    public Map findById(@PathVariable Long id){
        return educationService.findById(id);
    }
    @PutMapping("/api/v1/career/{id}")
    public Long update(@PathVariable Long id, @RequestBody EducationUpdateRequestDto requestDto){
        return educationService.update(id,requestDto);
    }
    @DeleteMapping("/api/v1/career/{id}")
    public Long delete(@PathVariable Long id){
        return educationService.delete(id);
    }
}
