package com.bgwb0007.intro.springboot.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CertificateUpdateRequestDto {
    private String title;
    private String content;
    private String certification;
    private String licenseDate;
    private String endDate;
    private String imageFileName;
    private String imagePath;
    @Builder
    public CertificateUpdateRequestDto(String title, String content, String certification, String licenseDate, String endDate, String imageFileName, String imagePath) {
        this.title = title;
        this.content = content;
        this.certification = certification;
        this.licenseDate = licenseDate;
        this.endDate = endDate;
        this.imageFileName = imageFileName;
        this.imagePath = imagePath;
    }
}
