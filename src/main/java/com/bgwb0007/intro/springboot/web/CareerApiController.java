package com.bgwb0007.intro.springboot.web;

import com.bgwb0007.intro.springboot.service.CareerService;
import com.bgwb0007.intro.springboot.web.dto.CareerSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CareerApiController {
    private final CareerService careerService;

    @PostMapping("/api/v1/career")
    public Long save(@RequestBody CareerSaveRequestDto requestDto){
        return careerService.save(requestDto);
    }
}
