package com.bgwb0007.intro.springboot.domain.profiles;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProfilesRepositoryTest {
    @Autowired
    ProfilesRepository profilesRepository;

    @After
    public void cleanup(){
        profilesRepository.deleteAll();
    }

    @Test
    public void 프로필_저장_후_전체조회(){
        String name = "서형";
        String instagramId = "west_brotherrr";
        String instagramUrl = "http://www.instagram.com";
        String linkedInId = "bgwb0001@naver.com";
        String linkedInUrl = "http://www.linkedin.com";
        String gitHubId = "bgwb0002@naver.com";
        String gitHubUrl = "http://www.github.com";
        String email = "bgwb0003@naver.com";
        String phone = "010-7365-9430";
        String content = "프로필입니다.!@";

        profilesRepository.save(Profiles.builder()
                .name(name)
                .instagramId(instagramId)
                .instagramUrl(instagramUrl)
                .linkedInId(linkedInId)
                .linkedInUrl(linkedInUrl)
                .gitHubId(gitHubId)
                .gitHubUrl(gitHubUrl)
                .email(email)
                .phone(phone)
                .content(content)
                .build());
        List<Profiles> profilesList = profilesRepository.findAll();

        Profiles profiles = profilesList.get(0);
        assertThat(profiles.getName()).isEqualTo(name);
        assertThat(profiles.getInstagramId()).isEqualTo(instagramId);
        assertThat(profiles.getInstagramUrl()).isEqualTo(instagramUrl);
        assertThat(profiles.getLinkedInId()).isEqualTo(linkedInId);
        assertThat(profiles.getLinkedInUrl()).isEqualTo(linkedInUrl);
        assertThat(profiles.getGitHubId()).isEqualTo(gitHubId);
        assertThat(profiles.getGitHubUrl()).isEqualTo(gitHubUrl);
        assertThat(profiles.getEmail()).isEqualTo(email);
        assertThat(profiles.getPhone()).isEqualTo(phone);
        assertThat(profiles.getContent()).isEqualTo(content);
    }

}
