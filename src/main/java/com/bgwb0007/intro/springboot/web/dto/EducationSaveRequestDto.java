package com.bgwb0007.intro.springboot.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class EducationSaveRequestDto {

    private String title;
    private String content;
    private String startDate;
    private String endDate;

    public EducationSaveRequestDto(String title, String content, String startDate, String endDate) {
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
