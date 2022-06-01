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
//                .instagramId("west_bro")
//                .instagramUrl("instagram.com")
//                .linkedInId("linedin@naver.com")
//                .linkedInUrl("linkedIN.com")
//                .gitHubId("깃수정전아이디")
//                .gitHubUrl("github.com")
//                .email("수정전이메일")
//                .phone("010-2222-3333")
                .content("수정전 content")
                .build());

        Long updateId = savedProfiles.getId();
        String name = "수정후이름";
        String instagramId = "수정후인스타아이디";
        String instagramUrl = "수정후인스타주소";
        String linkedInId = "수정후링크드인아이디";
        String linkedInUrl = "수정후링크드인주소";
        String gitHubId = "수정후깃허브아이디";
        String gitHubUrl = "수정후깃허브주소";
        String email = "수정후이메일";
        String phone = "수정후폰";
        String content = "수정후컨텐츠";

        ProfilesUpdateRequestDto requestDto = ProfilesUpdateRequestDto.builder()
                .name(name)
//                .instagramId(instagramId)
//                .instagramUrl(instagramUrl)
//                .linkedInId(linkedInId)
//                .linkedInUrl(linkedInUrl)
//                .gitHubId(gitHubId)
//                .gitHubUrl(gitHubUrl)
//                .email(email)
//                .phone(phone)
                .content(content)
                .build();
        String url = "http://localhost:"+port+"/api/v1/profiles/" + updateId;
        HttpEntity<ProfilesUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Profiles> all = profilesRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(name);
//        assertThat(all.get(0).getInstagramId()).isEqualTo(instagramId);
//        assertThat(all.get(0).getInstagramUrl()).isEqualTo(instagramUrl);
//        assertThat(all.get(0).getLinkedInId()).isEqualTo(linkedInId);
//        assertThat(all.get(0).getLinkedInUrl()).isEqualTo(linkedInUrl);
//        assertThat(all.get(0).getGitHubId()).isEqualTo(gitHubId);
//        assertThat(all.get(0).getGitHubUrl()).isEqualTo(gitHubUrl);
//        assertThat(all.get(0).getEmail()).isEqualTo(email);
//        assertThat(all.get(0).getPhone()).isEqualTo(phone);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }
}
