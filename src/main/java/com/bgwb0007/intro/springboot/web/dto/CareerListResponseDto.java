package com.bgwb0007.intro.springboot.web.dto;

import com.bgwb0007.intro.springboot.domain.resume.Career;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class CareerListResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDate startDate;
    private LocalDate endDate;

    public CareerListResponseDto(Career career) {
        this.id = career.getId();
        this.title = career.getTitle();
        this.content = career.getContent();
        this.startDate = career.getStartDate();
        this.endDate = career.getEndDate();
    }
}
