package com.bgwb0007.intro.springboot.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class EducationUpdateRequestDto {
    private String title;
    private String content;
    private String startDate;
    private String endDate;
    @Builder
    public EducationUpdateRequestDto(String title, String content, String startDate, String endDate) {
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
