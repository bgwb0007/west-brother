package com.bgwb0007.intro.springboot.web.dto;

import com.bgwb0007.intro.springboot.domain.resume.Career;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;

public class CareerSaveRequestDto {
    private String title;
    private String content;
    private LocalDate startDate;
    private LocalDate endDate;

    @Builder
    public CareerSaveRequestDto(String title, String content, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Career toEntity(){
        return Career.builder()
                .title(title)
                .content(content)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }

}
