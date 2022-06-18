package com.bgwb0007.intro.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class CareerUpdateRequestDto {

    private String title;
    private String content;
    private String startDate;    // 입사일자
    private String endDate;      // 퇴사일자

    @Builder
    public CareerUpdateRequestDto(String title, String content, String startDate, String endDate) {
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
