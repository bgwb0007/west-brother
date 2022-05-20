package com.bgwb0007.intro.springboot.web.dto;

import com.bgwb0007.intro.springboot.domain.profiles.Profiles;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProfilesListResponseDto {
    private Long id;
    private String name;
    private String instagram;
    private String linkedIn;
    private String gitHub;
    private String email;
    private String content;
    private LocalDateTime modifiedDate;

    public ProfilesListResponseDto(Profiles entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.instagram = entity.getInstagram();
        this.linkedIn = entity.getLinkedIn();
        this.gitHub = entity.getGitHub();
        this.email = entity.getEmail();
        this.content = entity.getContent();
        this.modifiedDate = entity.getModifiedDate();
    }
}
