package com.bgwb0007.intro.springboot.web;

import com.bgwb0007.intro.springboot.domain.resume.Project;
import com.bgwb0007.intro.springboot.domain.resume.ProjectRepository;
import com.bgwb0007.intro.springboot.web.dto.ProjectSaveRequestDto;
import com.bgwb0007.intro.springboot.web.dto.ProjectUpdateRequestDto;
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
public class ProjectApiControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ProjectRepository projectRepository;
    @After
    public void tearDown() throws Exception{
        projectRepository.deleteAll();
    }

    @Test
    public void Project_등록하기_종료날짜존재() throws Exception {
        //given
        String title = "KB카드 홈 리뉴얼";
        String content = "kb카드";
        String contentDetail = "홈 리뉴얼";
        String startDate = "2021-09-05";
        String endDate = "2022-07-18";

        ProjectSaveRequestDto requestDto = ProjectSaveRequestDto.builder()
                .title(title)
                .content(content)
                .contentDetail(contentDetail)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        //when
        String url = "http://localhost:" + port + "/api/v1/project";
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto,Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList.get(0).getTitle()).isEqualTo(title);
        assertThat(projectList.get(0).getContent()).isEqualTo(content);
        assertThat(projectList.get(0).getContentDetail()).isEqualTo(contentDetail);
        assertThat(projectList.get(0).getStartDate()).isEqualTo(LocalDate.parse(startDate));
        assertThat(projectList.get(0).getEndDate()).isEqualTo(LocalDate.parse(endDate));
    }

    @Test
    public void Project_등록하기_종료날짜_공백() throws Exception {
        //given
        String title = "KB카드 홈 리뉴얼";
        String content = "kb카드";
        String contentDetail = "홈 리뉴얼";
        String startDate = "2021-09-05";
        String endDate = "";

        ProjectSaveRequestDto requestDto = ProjectSaveRequestDto.builder()
                .title(title)
                .content(content)
                .contentDetail(contentDetail)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        //when
        String url = "http://localhost:" + port + "/api/v1/project";
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto,Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList.get(0).getTitle()).isEqualTo(title);
        assertThat(projectList.get(0).getContent()).isEqualTo(content);
        assertThat(projectList.get(0).getContentDetail()).isEqualTo(contentDetail);
        assertThat(projectList.get(0).getStartDate()).isEqualTo(LocalDate.parse(startDate));
        assertThat(projectList.get(0).getEndDate()).isNull();
    }

    @Test
    public void Project_전체조회하기() throws Exception{
        //given
        String title = "KB카드 홈 리뉴얼";
        String content = "kb카드";
        String contentDetail = "홈 리뉴얼";
        String startDate = "2021-09-05";
        String endDate = "2022-04-08";

        Long id = projectRepository.save(Project.builder()
                .title(title)
                .content(content)
                .contentDetail(contentDetail)
                .startDate(LocalDate.parse(startDate))
                .endDate(LocalDate.parse(endDate))
                .build()).getId();
        ObjectMapper objectMapper = new ObjectMapper();
        //when
        String url = "http://localhost:" + port + "/api/v1/project";
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
        assertThat(retMap.get("contentDetail")).isEqualTo(contentDetail);
        assertThat(retMap.get("startDate")).isEqualTo(startDate);
        assertThat(retMap.get("endDate")).isEqualTo(endDate);
    }

    @Test
    public void Project_수정하기() throws Exception{
        //given
        Long id =projectRepository.save(Project.builder()
                .title("title")
                .content("content")
                .contentDetail("contentDetail")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build()).getId();

        String title = "KB카드 홈 리뉴얼";
        String content = "kb카드";
        String contentDetail = "홈 리뉴얼";
        String startDate = "2021-09-05";
        String endDate = "2022-04-08";
        ProjectUpdateRequestDto requestDto = ProjectUpdateRequestDto.builder()
                .title(title)
                .content(content)
                .contentDetail(contentDetail)
                .startDate(startDate)
                .endDate(endDate)
                .build();
        //when
        String url = "http://localhost:" + port + "/api/v1/project/" + id;
        restTemplate.put(url,requestDto,Long.class);

        //then
        Project updatedProject = projectRepository.findById(id)
                .orElse(Project.builder()
                        .title("@수정실패_해당id로 조회불가").build());
//        System.out.println("################");
//        System.out.println(updatedCareer.getTitle());
        assertThat(updatedProject.getTitle()).isEqualTo(title);
        assertThat(updatedProject.getContent()).isEqualTo(content);
        assertThat(updatedProject.getContentDetail()).isEqualTo(contentDetail);
        assertThat(updatedProject.getStartDate()).isEqualTo(LocalDate.parse(startDate));
        assertThat(updatedProject.getEndDate()).isEqualTo(LocalDate.parse(endDate));
    }

    @Test
    public void Project_삭제하기() throws Exception{
        //given
        Long id =projectRepository.save(Project.builder()
                .title("title")
                .content("content")
                .contentDetail("contentDetail")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build()).getId();
        //when
        String url = "http://localhost:" + port + "/api/v1/project/" + id;
        restTemplate.delete(url,Long.class);

        //then
        String delTitle = "삭제완료";
        Project deletedProject = projectRepository.findById(id)
                .orElse(Project.builder()
                        .title(delTitle).build());
        assertThat(deletedProject.getTitle()).isEqualTo(delTitle);
    }

}
