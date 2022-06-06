package com.bgwb0007.intro.springboot.web;

import com.bgwb0007.intro.springboot.domain.contact.Contact;
import com.bgwb0007.intro.springboot.domain.contact.ContactRepository;
import com.bgwb0007.intro.springboot.domain.profiles.Profiles;
import com.bgwb0007.intro.springboot.domain.profiles.ProfilesRepository;
import com.bgwb0007.intro.springboot.web.dto.ContactSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContactApiControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ProfilesRepository profilesRepository;
    @After
    public void tearDown() throws Exception{
        contactRepository.deleteAll();
    }
    @Test
    public void Contact_등록하기() throws Exception {
        //given
        Profiles profiles =profilesRepository.save(Profiles.builder()
                .name("임서형")
                .content("it개발자")
                .photoPath("/images/dssdf")
                .photoFileName("tjgudss.png")
                .build());
        String name = "인스타";
        String engName = "instagram";
        String logoFileName = "aaa.png";
        String logoPath = "/dfasf/asdf";
        String siteId = "west-brotherr";
        String siteUrl = "https:insta";
        Integer sortOrder = 20;
        ContactSaveRequestDto requestDto = ContactSaveRequestDto.builder()
                .name(name)
                .engName(engName)
                .logoFileName(logoFileName)
                .logoPath(logoPath)
                .siteId(siteId)
                .siteUrl(siteUrl)
                .sortOrder(sortOrder)
                .profilesId(profiles.getId())
                .build();

        //when
        String url = "http://localhost:" + port + "/api/v1/profiles/contact";
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto,Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Contact> all = contactRepository.findAllOrderBySortOrderAsc();
        assertThat(all.get(0).getName()).isEqualTo(name);
        assertThat(all.get(0).getSiteId()).isEqualTo(siteId);

    }
}