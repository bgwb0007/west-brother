package com.bgwb0007.intro.springboot.web.dto;

import com.bgwb0007.intro.springboot.domain.profiles.Profiles;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProfilesListResponseDto {
    private Long id;
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
    private LocalDateTime modifiedDate;

    public ProfilesListResponseDto(Profiles entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.instagramId = entity.getInstagramId();
        this.instagramUrl = entity.getInstagramUrl();
        this.linkedInId = entity.getLinkedInId();
        this.linkedInUrl = entity.getLinkedInUrl();
        this.gitHubId = entity.getGitHubId();
        this.gitHubUrl = entity.getGitHubUrl();
        this.email = entity.getEmail();
        this.phone = entity.getPhone();
        this.content = entity.getContent();
        this.modifiedDate = entity.getModifiedDate();
    }
}
