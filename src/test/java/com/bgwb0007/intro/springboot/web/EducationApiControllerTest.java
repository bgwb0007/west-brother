package com.bgwb0007.intro.springboot.web;

import com.bgwb0007.intro.springboot.domain.resume.Career;
import com.bgwb0007.intro.springboot.domain.resume.Education;
import com.bgwb0007.intro.springboot.domain.resume.EducationRepository;
import com.bgwb0007.intro.springboot.web.dto.CareerUpdateRequestDto;
import com.bgwb0007.intro.springboot.web.dto.EducationSaveRequestDto;
import com.bgwb0007.intro.springboot.web.dto.EducationUpdateRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EducationApiControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private EducationRepository educationRepository;
    @After
    public void tearDown() throws Exception{
        educationRepository.deleteAll();
    }

    @Test
    public void Education_등록하기_종료날짜존재() throws Exception {
        //given
        String title = "경희대학교";
        String content = "산업경영공학";
        String startDate = "2014-03-01";
        String endDate = "2020-08-18";

        EducationSaveRequestDto requestDto = EducationSaveRequestDto.builder()
                .title(title)
                .content(content)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        //when
        String url = "http://localhost:" + port + "/api/v1/education";
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto,Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Education> educationList = educationRepository.findAll();
        assertThat(educationList.get(0).getTitle()).isEqualTo(title);
        assertThat(educationList.get(0).getContent()).isEqualTo(content);
        assertThat(educationList.get(0).getStartDate()).isEqualTo(LocalDate.parse(startDate));
        assertThat(educationList.get(0).getEndDate()).isEqualTo(LocalDate.parse(endDate));
    }

    @Test
    public void Education_등록하기_종료날짜_공백() throws Exception {
        //given
        String title = "경희대학교2";
        String content = "산업경영공학2";
        String startDate = "2014-04-01";
        String endDate = "";

        EducationSaveRequestDto requestDto = EducationSaveRequestDto.builder()
                .title(title)
                .content(content)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        //when
        String url = "http://localhost:" + port + "/api/v1/education";
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto,Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Education> educationList = educationRepository.findAll();
        assertThat(educationList.get(0).getTitle()).isEqualTo(title);
        assertThat(educationList.get(0).getContent()).isEqualTo(content);
        assertThat(educationList.get(0).getStartDate()).isEqualTo(LocalDate.parse(startDate));
        assertThat(educationList.get(0).getEndDate()).isNull();
    }

    @Test
    public void Education_전체조회하기() throws Exception{
        //given
        String title = "경희대학교3";
        String content = "산업경영공학3";
        String startDate = "2014-04-01";
        String endDate = "2022-07-18";

        Long id = educationRepository.save(Education.builder()
                .title(title)
                .content(content)
                .startDate(LocalDate.parse(startDate))
                .endDate(LocalDate.parse(endDate))
                .build()).getId();
        ObjectMapper objectMapper = new ObjectMapper();
        //when
        String url = "http://localhost:" + port + "/api/v1/education";
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(url,List.class);
        HashMap<String ,String> retMap = objectMapper.convertValue(responseEntity.getBody().get(0),HashMap.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        System.out.println("##responseEntity.getBody():");
//        System.out.println(responseEntity.getBody());
//        System.out.println(retMap.get("id"));
        assertThat(retMap.get("id")).isEqualTo(String.valueOf(id));
        assertThat(retMap.get("title")).isEqualTo(title);
        assertThat(retMap.get("content")).isEqualTo(content);
        assertThat(retMap.get("startDate")).isEqualTo(startDate);
        assertThat(retMap.get("endDate")).isEqualTo(endDate);
    }

    @Test
    public void Education_수정하기() throws Exception{
        //given
        Long id =educationRepository.save(Education.builder()
                .title("title")
                .content("content")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build()).getId();

        String title = "경희대학교4";
        String content = "산업경영공학4";
        String startDate = "2015-05-01";
        String endDate = "2020-07-18";
        EducationUpdateRequestDto requestDto = EducationUpdateRequestDto.builder()
                .title(title)
                .content(content)
                .startDate(startDate)
                .endDate(endDate)
                .build();
        //when
        String url = "http://localhost:" + port + "/api/v1/education/" + id;
        restTemplate.put(url,requestDto,Long.class);

        //then
        Education updatedEducation = educationRepository.findById(id)
                .orElse(Education.builder()
                        .title("@수정실패_해당id로 조회불가").build());
//        System.out.println("################");
//        System.out.println(updatedEducation.getTitle());
        assertThat(updatedEducation.getTitle()).isEqualTo(title);
        assertThat(updatedEducation.getContent()).isEqualTo(content);
        assertThat(updatedEducation.getStartDate()).isEqualTo(LocalDate.parse(startDate));
        assertThat(updatedEducation.getEndDate()).isEqualTo(LocalDate.parse(endDate));
    }

    @Test
    public void Education_삭제하기() throws Exception{
        //given
        Long id =educationRepository.save(Education.builder()
                .title("title")
                .content("content")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build()).getId();
        //when
        String url = "http://localhost:" + port + "/api/v1/education/" + id;
        restTemplate.delete(url,Long.class);

        //then
        String delTitle = "삭제완료";
        Education deletedEducation = educationRepository.findById(id)
                .orElse(Education.builder()
                        .title(delTitle).build());
        assertThat(deletedEducation.getTitle()).isEqualTo(delTitle);
    }

}
