package com.bgwb0007.intro.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class ProfilesUpdateRequestDto {
    private String name;
    private String instagramId;
    private String instagramUrl;
    private String linkedInId;
    private String linkedInUrl;
    private String gitHubId;
    private String gitHubUrl;
    private String email;
    private String phone;
    private String content;

    @Builder
    public ProfilesUpdateRequestDto(String name, String instagramId, String instagramUrl, String linkedInId, String linkedInUrl, String gitHubId, String gitHubUrl, String email, String phone, String content) {
        this.name = name;
        this.instagramId = instagramId;
        this.instagramUrl = instagramUrl;
        this.linkedInId = linkedInId;
        this.linkedInUrl = linkedInUrl;
        this.gitHubId = gitHubId;
        this.gitHubUrl = gitHubUrl;
        this.email = email;
        this.phone = phone;
        this.content = content;
    }
}
