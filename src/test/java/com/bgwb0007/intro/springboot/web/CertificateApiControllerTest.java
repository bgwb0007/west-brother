package com.bgwb0007.intro.springboot.web;

import com.bgwb0007.intro.springboot.domain.resume.Certificate;
import com.bgwb0007.intro.springboot.domain.resume.CertificateRepository;
import com.bgwb0007.intro.springboot.web.dto.CertificateSaveRequestDto;
import com.bgwb0007.intro.springboot.web.dto.CertificateUpdateRequestDto;
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
public class CertificateApiControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private CertificateRepository certificateRepository;
    @After
    public void tearDown() throws Exception{
        certificateRepository.deleteAll();
    }

    @Test
    public void Certificate_등록하기_종료날짜존재() throws Exception {
        //given
        String title = "정보처리기사";
        String content = "기사";
        String certification = "기사";
        String licenseDate = "2014-03-01";
        String endDate = "2020-08-18";
        String imageFileName = "cer1.jgp";
        String imagePath = "/image/app/cer1.jgp";

        CertificateSaveRequestDto requestDto = CertificateSaveRequestDto.builder()
                .title(title)
                .content(content)
                .certification(certification)
                .licenseDate(licenseDate)
                .endDate(endDate)
                .imageFileName(imageFileName)
                .imagePath(imagePath)
                .build();

        //when
        String url = "http://localhost:" + port + "/api/v1/certificate";
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto,Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Certificate> certificateList = certificateRepository.findAll();
        assertThat(certificateList.get(0).getTitle()).isEqualTo(title);
        assertThat(certificateList.get(0).getContent()).isEqualTo(content);
        assertThat(certificateList.get(0).getCertification()).isEqualTo(certification);
        assertThat(certificateList.get(0).getEndDate()).isEqualTo(LocalDate.parse(endDate));
        assertThat(certificateList.get(0).getImageFileName()).isEqualTo(imageFileName);
        assertThat(certificateList.get(0).getImagePath()).isEqualTo(imagePath);
    }

    @Test
    public void Certificate_등록하기_종료날짜_공백() throws Exception {
        //given
        String title = "정보처리기사";
        String content = "기사";
        String certification = "기사";
        String licenseDate = "2014-03-01";
        String endDate = "";
        String imageFileName = "cer1.jgp";
        String imagePath = "/image/app/cer1.jgp";

        CertificateSaveRequestDto requestDto = CertificateSaveRequestDto.builder()
                .title(title)
                .content(content)
                .certification(certification)
                .licenseDate(licenseDate)
                .endDate(endDate)
                .imageFileName(imageFileName)
                .imagePath(imagePath)
                .build();

        //when
        String url = "http://localhost:" + port + "/api/v1/certificate";
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto,Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Certificate> certificateList = certificateRepository.findAll();
        assertThat(certificateList.get(0).getTitle()).isEqualTo(title);
        assertThat(certificateList.get(0).getContent()).isEqualTo(content);
        assertThat(certificateList.get(0).getCertification()).isEqualTo(certification);
        assertThat(certificateList.get(0).getEndDate()).isNull();
        assertThat(certificateList.get(0).getImageFileName()).isEqualTo(imageFileName);
        assertThat(certificateList.get(0).getImagePath()).isEqualTo(imagePath);
    }

    @Test
    public void Certificate_전체조회하기() throws Exception{
        //given
        String title = "정보처리기사";
        String content = "기사";
        String certification = "기사";
        String licenseDate = "2014-03-01";
        String endDate = "";
        String imageFileName = "cer1.jgp";
        String imagePath = "/image/app/cer1.jgp";

        Long id = certificateRepository.save(Certificate.builder()
                .title(title)
                .content(content)
                .certification(certification)
                .licenseDate(LocalDate.parse(licenseDate))
                .endDate(null)
                .imageFileName(imageFileName)
                .imagePath(imagePath)
                .build()).getId();
        ObjectMapper objectMapper = new ObjectMapper();
        //when
        String url = "http://localhost:" + port + "/api/v1/certificate";
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
        assertThat(retMap.get("certification")).isEqualTo(certification);
        assertThat(retMap.get("licenseDate")).isEqualTo(licenseDate);
        assertThat(retMap.get("endDate")).isEqualTo("null");
        assertThat(retMap.get("imageFileName")).isEqualTo(imageFileName);
        assertThat(retMap.get("imagePath")).isEqualTo(imagePath);
    }

    @Test
    public void Certificate_수정하기() throws Exception{
        //given
        Long id =certificateRepository.save(Certificate.builder()
                .title("title")
                .content("content")
                .certification("certification")
                .licenseDate(LocalDate.now())
                .endDate(LocalDate.now())
                .imageFileName("imageFileName")
                .imagePath("imagePath")
                .build()).getId();

        String title = "품질경영기사";
        String content = "기사";
        String certification = "한국";
        String licenseDate = "2020-06-01";
        String endDate = "";
        String imageFileName = "cer1.jgp";
        String imagePath = "/image/app/cer1.jgp";
        CertificateUpdateRequestDto requestDto = CertificateUpdateRequestDto.builder()
                .title(title)
                .content(content)
                .certification(certification)
                .licenseDate(licenseDate)
                .endDate(endDate)
                .imageFileName(imageFileName)
                .imagePath(imagePath)
                .build();
        //when
        String url = "http://localhost:" + port + "/api/v1/certificate/" + id;
        restTemplate.put(url,requestDto,Long.class);

        //then
        Certificate updatedCertificate = certificateRepository.findById(id)
                .orElse(Certificate.builder()
                        .title("@수정실패_해당id로 조회불가").build());
//        System.out.println("################");
//        System.out.println(updatedEducation.getTitle());
        assertThat(updatedCertificate.getTitle()).isEqualTo(title);
        assertThat(updatedCertificate.getContent()).isEqualTo(content);
        assertThat(updatedCertificate.getCertification()).isEqualTo(certification);
        assertThat(updatedCertificate.getLicenseDate()).isEqualTo(LocalDate.parse(licenseDate));
        assertThat(updatedCertificate.getEndDate()).isNull();
        assertThat(updatedCertificate.getImageFileName()).isEqualTo(imageFileName);
        assertThat(updatedCertificate.getImagePath()).isEqualTo(imagePath);

    }

    @Test
    public void Certificate_삭제하기() throws Exception{
        //given
        Long id =certificateRepository.save(Certificate.builder()
                .title("title")
                .content("content")
                .certification("certification")
                .licenseDate(LocalDate.now())
                .endDate(LocalDate.now())
                .imageFileName("imageFileName")
                .imagePath("imagePath")
                .build()).getId();
        //when
        String url = "http://localhost:" + port + "/api/v1/certificate/" + id;
        restTemplate.delete(url,Long.class);

        //then
        String delTitle = "삭제완료";
        Certificate deletedCertificate = certificateRepository.findById(id)
                .orElse(Certificate.builder()
                        .title(delTitle).build());
        assertThat(deletedCertificate.getTitle()).isEqualTo(delTitle);
    }

}
