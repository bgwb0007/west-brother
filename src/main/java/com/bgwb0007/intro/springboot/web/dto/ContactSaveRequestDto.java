package com.bgwb0007.intro.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ContactSaveRequestDto {
    private String name;
    private String engName;
    private String logoFileName;
    private String logoPath;
    private String siteId;
    private String siteUrl;
    private Integer sortOrder;
    private String pageGubun;

    @Builder
    public ContactSaveRequestDto(String name, String engName, String logoFileName, String logoPath, String siteId, String siteUrl, Integer sortOrder, String pageGubun) {
        this.name = name;
        this.engName = engName;
        this.logoFileName = logoFileName;
        this.logoPath = logoPath;
        this.siteId = siteId;
        this.siteUrl = siteUrl;
        this.sortOrder = sortOrder;
        this.pageGubun = pageGubun;
    }
}
