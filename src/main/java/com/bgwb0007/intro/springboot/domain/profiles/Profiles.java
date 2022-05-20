package com.bgwb0007.intro.springboot.domain.profiles;

import com.bgwb0007.intro.springboot.domain.BaseTimeEntity;
import com.bgwb0007.intro.springboot.web.dto.ProfilesUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Profiles extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 100)
    private String instagramId;
    @Column(length = 250)
    private String instagramUrl;
    @Column(length = 100)
    private String linkedInId;
    @Column(length = 250)
    private String linkedInUrl;
    @Column(length = 100)
    private String gitHubId;
    @Column(length = 250)
    private String gitHubUrl;
    @Column(length = 100)
    private String email;
    @Column(length = 100)
    private String phone;
    @Column(columnDefinition = "TEXT")
    private String content;
    @Builder
    public Profiles(String name, String instagramId, String instagramUrl, String linkedInId, String linkedInUrl, String gitHubId, String gitHubUrl, String email, String phone, String content) {
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
    public void update(ProfilesUpdateRequestDto requestDto){
        this.name = requestDto.getName();
        this.instagramId = requestDto.getInstagramId();
        this.instagramUrl = requestDto.getInstagramUrl();
        this.linkedInId = requestDto.getLinkedInId();
        this.linkedInUrl = requestDto.getLinkedInUrl();
        this.gitHubId = requestDto.getGitHubId();
        this.gitHubUrl = requestDto.getGitHubUrl();
        this.email = requestDto.getEmail();
        this.phone = requestDto.getPhone();
        this.content = requestDto.getContent();
    }
}
