package com.bgwb0007.intro.springboot.web.dto;

import com.bgwb0007.intro.springboot.domain.profiles.ProfilesDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@NoArgsConstructor
public class ProfilesUpdateRequestDto {
    private String name;
    private String content;
    private String photoFileName;
    private String photoPath;

    @Builder
    public ProfilesUpdateRequestDto(String name, String content, String photoFileName, String photoPath) {
        this.name = name;
        this.content = content;
        this.photoFileName = photoFileName;
        this.photoPath = photoPath;
    }
}
