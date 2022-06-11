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
        String content1 = "프로필입니다.!@";
        String content2 = "프로필입니다.!@2222222222";
        String photoFileName = "메인이미지.png";
        String photoPath = "이미지 경로";
        String pageGubun = "이미지 경로";

        profilesRepository.save(Profiles.builder()
                .name(name)
                .content1(content1)
                .content2(content2)
                .photoFileName(photoFileName)
                .photoPath(photoPath)
                .pageGubun(pageGubun)
                .build());
        List<Profiles> profilesList = profilesRepository.findAll();

        Profiles profiles = profilesList.get(0);
        assertThat(profiles.getName()).isEqualTo(name);
        assertThat(profiles.getContent1()).isEqualTo(content1);
        assertThat(profiles.getContent2()).isEqualTo(content2);
        assertThat(profiles.getPageGubun()).isEqualTo(pageGubun);
    }

}
