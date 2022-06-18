package com.bgwb0007.intro.springboot.web;

import com.bgwb0007.intro.springboot.domain.resume.Career;
import com.bgwb0007.intro.springboot.domain.resume.CareerRepository;
import com.bgwb0007.intro.springboot.web.dto.CareerSaveRequestDto;
import com.bgwb0007.intro.springboot.web.dto.CareerUpdateRequestDto;
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
public class CareerApiControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private CareerRepository careerRepository;
    @After
    public void tearDown() throws Exception{
        careerRepository.deleteAll();
    }

    @Test
    public void Career_등록하기_종료날짜존재() throws Exception {
        //given
        String title = "lgcns 클라우드개발";
        String content = "개발3팀";
        String startDate = "2021-07-18";
        String endDate = "2022-07-18";

        CareerSaveRequestDto requestDto = CareerSaveRequestDto.builder()
                .title(title)
                .content(content)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        //when
        String url = "http://localhost:" + port + "/api/v1/career";
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto,Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Career> careerList = careerRepository.findAll();
        assertThat(careerList.get(0).getTitle()).isEqualTo(title);
        assertThat(careerList.get(0).getContent()).isEqualTo(content);
        assertThat(careerList.get(0).getStartDate()).isEqualTo(LocalDate.parse(startDate));
        assertThat(careerList.get(0).getEndDate()).isEqualTo(LocalDate.parse(endDate));
    }

    @Test
    public void Career_등록하기_종료날짜_공백() throws Exception {
        //given
        String title = "lgcns 클라우드개발";
        String content = "개발3팀";
        String startDate = "2021-07-18";
        String endDate = "";

        CareerSaveRequestDto requestDto = CareerSaveRequestDto.builder()
                .title(title)
                .content(content)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        //when
        String url = "http://localhost:" + port + "/api/v1/career";
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto,Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Career> careerList = careerRepository.findAll();
        assertThat(careerList.get(0).getTitle()).isEqualTo(title);
        assertThat(careerList.get(0).getContent()).isEqualTo(content);
        assertThat(careerList.get(0).getStartDate()).isEqualTo(LocalDate.parse(startDate));

        assertThat(careerList.get(0).getEndDate()).isNull();
    }

    @Test
    public void Career_전체조회하기() throws Exception{
        //given
        String title = "lgcns 클라우드개발";
        String content = "개발3팀";
        String startDate = "2021-07-18";
        String endDate = "2022-07-18";

        Long id = careerRepository.save(Career.builder()
                .title(title)
                .content(content)
                .startDate(LocalDate.parse(startDate))
                .endDate(LocalDate.parse(endDate))
                .build()).getId();
        ObjectMapper objectMapper = new ObjectMapper();
        //when
        String url = "http://localhost:" + port + "/api/v1/career";
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
    public void Career_수정하기() throws Exception{
        //given
        Long id =careerRepository.save(Career.builder()
                .title("title")
                .content("content")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build()).getId();

        String title = "LG CNS 클라우드개발";
        String content = "개발3팀";
        String startDate = "2021-07-18";
        String endDate = "2022-07-18";
        CareerUpdateRequestDto requestDto = CareerUpdateRequestDto.builder()
                .title(title)
                .content(content)
                .startDate(startDate)
                .endDate(endDate)
                .build();
        //when
        String url = "http://localhost:" + port + "/api/v1/career/" + id;
        restTemplate.put(url,requestDto,Long.class);

        //then
        Career updatedCareer = careerRepository.findById(id)
                .orElse(Career.builder()
                        .title("@수정실패_해당id로 조회불가").build());
//        System.out.println("################");
//        System.out.println(updatedCareer.getTitle());
        assertThat(updatedCareer.getTitle()).isEqualTo(title);
        assertThat(updatedCareer.getContent()).isEqualTo(content);
        assertThat(updatedCareer.getStartDate()).isEqualTo(LocalDate.parse(startDate));
        assertThat(updatedCareer.getEndDate()).isEqualTo(LocalDate.parse(endDate));
    }

    @Test
    public void Career_삭제하기() throws Exception{
        //given
        Long id =careerRepository.save(Career.builder()
                .title("title")
                .content("content")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build()).getId();
        //when
        String url = "http://localhost:" + port + "/api/v1/career/" + id;
        restTemplate.delete(url,Long.class);

        //then
        String delTitle = "삭제완료";
        Career updatedCareer = careerRepository.findById(id)
                .orElse(Career.builder()
                        .title(delTitle).build());
        assertThat(updatedCareer.getTitle()).isEqualTo(delTitle);
    }

}
