package com.bgwb0007.intro.springboot.web.dto;

import com.bgwb0007.intro.springboot.domain.profiles.Profiles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProfilesGetOneResponseDto {
    private Long id;
    private String name;
    private String content1;
    private String content2;
    private String photoFileName;
    private String photoPath;
    private String pageGubun;

    public ProfilesGetOneResponseDto(Profiles profiles) {
        this.id = profiles.getId();
        this.name = profiles.getName();
        this.content1 = profiles.getContent1();
        this.content2 = profiles.getContent2();
        this.photoFileName = profiles.getPhotoFileName();
        this.photoPath = profiles.getPhotoPath();
        this.pageGubun = profiles.getPageGubun();
    }

}
