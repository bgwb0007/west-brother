package com.bgwb0007.intro.springboot.web.dto;

import com.bgwb0007.intro.springboot.domain.resume.Career;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@NoArgsConstructor
@Getter
public class CareerSaveRequestDto {
    private String title;
    private String content;
    private String startDate;
    private String endDate;
    @Builder
    public CareerSaveRequestDto(String title, String content, String startDate, String endDate){
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
