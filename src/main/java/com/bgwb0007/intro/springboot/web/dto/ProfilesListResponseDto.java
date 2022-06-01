package com.bgwb0007.intro.springboot.web.dto;

import com.bgwb0007.intro.springboot.domain.profiles.ProfilesDetail;
import com.bgwb0007.intro.springboot.domain.profiles.Profiles;

import java.util.List;

public class ProfilesListResponseDto {
    private String name;
    private String content;
    private String photoFileName;
    private String photoPath;
    private List<ProfilesDetail> profilesDetailList;

    public ProfilesListResponseDto(Profiles profiles) {
        this.name = profiles.getName();
        this.content = profiles.getContent();
        this.photoFileName = profiles.getPhotoFileName();
        this.photoPath = profiles.getPhotoPath();
        this.profilesDetailList = profiles.getProfilesDetailList();
    }
}
