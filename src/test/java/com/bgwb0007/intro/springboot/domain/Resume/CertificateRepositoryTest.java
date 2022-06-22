package com.bgwb0007.intro.springboot.domain.Resume;

import com.bgwb0007.intro.springboot.domain.resume.Certificate;
import com.bgwb0007.intro.springboot.domain.resume.CertificateRepository;
import com.bgwb0007.intro.springboot.domain.resume.Education;
import com.bgwb0007.intro.springboot.domain.resume.EducationRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CertificateRepositoryTest {
    @Autowired
    CertificateRepository certificateRepository;

    @After
    public void cleanup(){certificateRepository.deleteAll();}

    @Test
    public void 자격증등록_하고_가져오기_endDate_null() throws Exception {
        //given
        String title = "정보처리기사";
        String content = "기사";
        String certification = "한국산업진흥원";
        LocalDate licenseDate = LocalDate.of(2020,12,31);
        LocalDate endDate = null;
        String imageFileName = "정처기자격증.png";
        String imagePath = "/image/app/정처기자격증.png";

        //when
        certificateRepository.save(Certificate.builder()
                .title(title)
                .content(content)
                .certification(certification)
                .licenseDate(licenseDate)
                .endDate(endDate)
                .imageFileName(imageFileName)
                .imagePath(imagePath)
                .build());
        List<Certificate> certificateList = certificateRepository.findAll();

        //then
        Certificate certificate = certificateList.get(0);
        assertThat(certificate.getTitle()).isEqualTo(title);
        assertThat(certificate.getContent()).isEqualTo(content);
        assertThat(certificate.getLicenseDate()).isEqualTo(licenseDate);
        assertThat(certificate.getEndDate()).isEqualTo(endDate);
        assertThat(certificate.getImageFileName()).isEqualTo(imageFileName);
        assertThat(certificate.getImagePath()).isEqualTo(imagePath);
    }

}
