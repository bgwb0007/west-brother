package com.bgwb0007.intro.springboot.web.dto;

import com.bgwb0007.intro.springboot.domain.profiles.Profiles;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor
public class ContactUpdateRequestDto {
    private String name;
    private String engName;
    private String logoFileName;
    private String logoPath;
    private String siteId;
    private String siteUrl;
    private Integer sortOrder;

    @Builder
    public ContactUpdateRequestDto(String name, String engName, String logoFileName, String logoPath, String siteId, String siteUrl, Integer sortOrder) {
        this.name = name;
        this.engName = engName;
        this.logoFileName = logoFileName;
        this.logoPath = logoPath;
        this.siteId = siteId;
        this.siteUrl = siteUrl;
        this.sortOrder = sortOrder;
    }
}
