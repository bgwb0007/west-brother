package com.bgwb0007.intro.springboot.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CertificateSaveRequestDto {
    private String title;
    private String content;
    private String certification;
    private String licenseDate;
    private String endDate;
    private String imageFileName;
    private String imagePath;
}
