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
        String instagram = "west_brotherrr";
        String linkedIn = "bgwb0001@naver.com";
        String gitHub = "bgwb0002@naver.com";
        String email = "bgwb0003@naver.com";
        String content = "프로필입니다.!@";

        profilesRepository.save(Profiles.builder()
                .name(name)
                .instagram(instagram)
                .linkedIn(linkedIn)
                .gitHub(gitHub)
                .email(email)
                .content(content)
                .build());
        List<Profiles> profilesList = profilesRepository.findAll();

        Profiles profiles = profilesList.get(0);
        assertThat(profiles.getName()).isEqualTo(name);
        assertThat(profiles.getInstagram()).isEqualTo(instagram);
        assertThat(profiles.getLinkedIn()).isEqualTo(linkedIn);
        assertThat(profiles.getGitHub()).isEqualTo(gitHub);
        assertThat(profiles.getEmail()).isEqualTo(email);
        assertThat(profiles.getContent()).isEqualTo(content);
    }

}
