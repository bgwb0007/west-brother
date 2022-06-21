package com.bgwb0007.intro.springboot.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CertificateUpdateRequestDto {
    private String title;
    private String content;
    private String certification;
    private String licenseDate;
    private String endDate;
    private String imageFileName;
    private String imagePath;
}
