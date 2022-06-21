package com.bgwb0007.intro.springboot.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProjectSaveRequestDto {
    private String title;
    private String content;
    private String contentDetail;
    private String startDate;
    private String endDate;
}
