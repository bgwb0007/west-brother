package com.bgwb0007.intro.springboot.web;

import com.bgwb0007.intro.springboot.service.CareerService;
import com.bgwb0007.intro.springboot.web.dto.CareerSaveRequestDto;
import com.bgwb0007.intro.springboot.web.dto.CareerUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class CareerApiController {
    private final CareerService careerService;

    @PostMapping("/api/v1/career")
    public Long save(@RequestBody CareerSaveRequestDto requestDto) { return careerService.save(requestDto);}
    @GetMapping("/api/v1/career")
    public List<Map> findAllForHtml(){
        return careerService.findAllForHtml();
    }
    @GetMapping("/api/v1/career/{id}")
    public Map findById(@PathVariable Long id){
        return careerService.findById(id);
    }
    @PutMapping("/api/v1/career/{id}")
    public Long update(@PathVariable Long id, @RequestBody CareerUpdateRequestDto requestDto){
        return careerService.update(id,requestDto);
    }
    @DeleteMapping("/api/v1/career/{id}")
    public Long delete(@PathVariable Long id){
        return careerService.delete(id);
    }
}
