package com.bgwb0007.intro.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class ProfilesUpdateRequestDto {

    private String name;
    private String content1;
    private String content2;
    private String photoFileName;
    private String photoPath;
    private String pageGubun;

    @Builder
    public ProfilesUpdateRequestDto(String name, String content1, String content2, String photoFileName, String photoPath, String pageGubun) {
        this.name = name;
        this.content1 = content1;
        this.content2 = content2;
        this.photoFileName = photoFileName;
        this.photoPath = photoPath;
        this.pageGubun = pageGubun;
    }
}
