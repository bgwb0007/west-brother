package com.bgwb0007.intro.springboot.web;

import com.bgwb0007.intro.springboot.domain.resume.*;
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
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ResumeApiControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private CareerRepository careerRepository;
    @Autowired
    private EducationRepository educationRepository;
    @Autowired
    private CertificateRepository certificateRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @After
    public void tearDown() throws Exception{
        careerRepository.deleteAll();
        educationRepository.deleteAll();
        certificateRepository.deleteAll();
        projectRepository.deleteAll();

    }

    @Test
    public void Certificate_전체조회하기() throws Exception{
        //given
        String Ctitle = "lgcns 클라우드개발";
        String Ccontent = "개발3팀";
        String CstartDate = "2021-07-18";
        String CendDate = "2022-07-18";
        Long Cid = careerRepository.save(Career.builder()
                .title(Ctitle)
                .content(Ccontent)
                .startDate(LocalDate.parse(CstartDate))
                .endDate(LocalDate.parse(CendDate))
                .build()).getId();

        String Etitle = "경희대학교3";
        String Econtent = "산업경영공학3";
        String EstartDate = "2014-04-01";
        String EendDate = "2022-07-18";
        Long Eid = educationRepository.save(Education.builder()
                .title(Etitle)
                .content(Econtent)
                .startDate(LocalDate.parse(EstartDate))
                .endDate(LocalDate.parse(EendDate))
                .build()).getId();

        String CERtitle = "정보처리기사";
        String CERcontent = "기사";
        String CERcertification = "기사";
        String CERlicenseDate = "2014-03-01";
        String CERimageFileName = "cer1.jgp";
        String CERimagePath = "/image/app/cer1.jgp";
        String CERRetEndDate = "null";
        Long CERid = certificateRepository.save(Certificate.builder()
                .title(CERtitle)
                .content(CERcontent)
                .certification(CERcertification)
                .licenseDate(LocalDate.parse(CERlicenseDate))
                .endDate(null)
                .imageFileName(CERimageFileName)
                .imagePath(CERimagePath)
                .build()).getId();

        String Ptitle = "KB카드 홈 리뉴얼";
        String Pcontent = "kb카드";
        String PcontentDetail = "홈 리뉴얼";
        String PstartDate = "2021-09-05";
        String PendDate = "2022-04-08";

        Long Pid = projectRepository.save(Project.builder()
                .title(Ptitle)
                .content(Pcontent)
                .contentDetail(PcontentDetail)
                .startDate(LocalDate.parse(PstartDate))
                .endDate(LocalDate.parse(PendDate))
                .build()).getId();

        ObjectMapper objectMapper = new ObjectMapper();
        //when
        String url = "http://localhost:" + port + "/api/v1/resume";
        ResponseEntity<Map> responseEntity = restTemplate.getForEntity(url,Map.class);
//        HashMap retMap = objectMapper.convertValue(responseEntity.getBody().get(0),HashMap.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        System.out.println("##responseEntity.getBody():");
//        System.out.println(responseEntity.getBody());
//        System.out.println(responseEntity.getBody().get("sub01"));
        List<Map> careerList = objectMapper.convertValue(responseEntity.getBody().get("careers"),List.class);
        assertThat(careerList.get(0).get("id")).isEqualTo(String.valueOf(Cid));
        assertThat(careerList.get(0).get("title")).isEqualTo(Ctitle);
        assertThat(careerList.get(0).get("content")).isEqualTo(Ccontent);
        assertThat(careerList.get(0).get("startDate")).isEqualTo(CstartDate);
        assertThat(careerList.get(0).get("endDate")).isEqualTo(CendDate);

        List<Map> educationList = objectMapper.convertValue(responseEntity.getBody().get("educations"),List.class);
        assertThat(educationList.get(0).get("id")).isEqualTo(String.valueOf(Eid));
        assertThat(educationList.get(0).get("title")).isEqualTo(Etitle);
        assertThat(educationList.get(0).get("content")).isEqualTo(Econtent);
        assertThat(educationList.get(0).get("startDate")).isEqualTo(EstartDate);
        assertThat(educationList.get(0).get("endDate")).isEqualTo(EendDate);

        List<Map> certificateList = objectMapper.convertValue(responseEntity.getBody().get("certificates"),List.class);
        assertThat(certificateList.get(0).get("id")).isEqualTo(String.valueOf(CERid));
        assertThat(certificateList.get(0).get("title")).isEqualTo(CERtitle);
        assertThat(certificateList.get(0).get("content")).isEqualTo(CERcontent);
        assertThat(certificateList.get(0).get("certification")).isEqualTo(CERcertification);
        assertThat(certificateList.get(0).get("licenseDate")).isEqualTo(CERlicenseDate);
        assertThat(certificateList.get(0).get("endDate")).isEqualTo(CERRetEndDate);
        assertThat(certificateList.get(0).get("imageFileName")).isEqualTo(CERimageFileName);
        assertThat(certificateList.get(0).get("imagePath")).isEqualTo(CERimagePath);

        List<Map> ProjectList = objectMapper.convertValue(responseEntity.getBody().get("projects"),List.class);
        assertThat(ProjectList.get(0).get("id")).isEqualTo(String.valueOf(Pid));
        assertThat(ProjectList.get(0).get("title")).isEqualTo(Ptitle);
        assertThat(ProjectList.get(0).get("content")).isEqualTo(Pcontent);
        assertThat(ProjectList.get(0).get("contentDetail")).isEqualTo(PcontentDetail);
        assertThat(ProjectList.get(0).get("startDate")).isEqualTo(PstartDate);
        assertThat(ProjectList.get(0).get("endDate")).isEqualTo(PendDate);



    }


}
