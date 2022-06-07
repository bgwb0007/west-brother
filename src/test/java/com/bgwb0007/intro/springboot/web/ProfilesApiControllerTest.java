package com.bgwb0007.intro.springboot.web;

import com.bgwb0007.intro.springboot.domain.profiles.Profiles;
import com.bgwb0007.intro.springboot.domain.profiles.ProfilesRepository;
import com.bgwb0007.intro.springboot.web.dto.ProfilesUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProfilesApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProfilesRepository profilesRepository;

    @After
    public void tearDown() throws Exception{
        profilesRepository.deleteAll();
    }
    @Test
    public void Profiles_수정하기() throws Exception {
        //given
        Profiles savedProfiles = profilesRepository.save(Profiles.builder()
                .name("수정전이름")
                .content("수정전 content")
                .photoFileName("수정전이미지파일.png")
                .photoPath("수정전 경로")
                .build());

        Long updateId = savedProfiles.getId();
        String name = "수정후이름";
        String content = "수정후컨텐츠";
        String photoFileName = "수정후 수정전이미지파일.png";
        String photoPath = "수정후 경로";

        ProfilesUpdateRequestDto requestDto = ProfilesUpdateRequestDto.builder()
                .name(name)
                .content(content)
                .photoFileName(photoFileName)
                .photoPath(photoPath)
                .build();

        String url = "http://localhost:" + port + "/api/v1/profiles/" + updateId;
        HttpEntity<ProfilesUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Profiles> all = profilesRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(name);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }
}
