package com.bgwb0007.intro.springboot.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProjectUpdateRequestDto {
    private String title;
    private String content;
    private String contentDetail;
    private String startDate;
    private String endDate;
    @Builder
    public ProjectUpdateRequestDto(String title, String content, String contentDetail, String startDate, String endDate) {
        this.title = title;
        this.content = content;
        this.contentDetail = contentDetail;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
