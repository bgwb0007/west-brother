package com.bgwb0007.intro.springboot.domain.Resume;

import com.bgwb0007.intro.springboot.domain.resume.Career;
import com.bgwb0007.intro.springboot.domain.resume.CareerRepository;
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
public class EducationRepositoryTest {
    @Autowired
    EducationRepository educationRepository;

    @After
    public void cleanup(){educationRepository.deleteAll();}

    @Test
    public void 학력등록_하고_가져오기() throws Exception {
        //given
        String title = "경희대학교";
        String content = "산업경영공학";
        LocalDate startDate = LocalDate.of(2014,03,01);
        LocalDate endDate = LocalDate.of(2020,8,01);

        //when
        educationRepository.save(Education.builder()
                .title(title)
                .content(content)
                .startDate(startDate)
                .endDate(endDate)
                .build());
        List<Education> educationList = educationRepository.findAll();

        //then
        Education education = educationList.get(0);
        assertThat(education.getTitle()).isEqualTo(title);
        assertThat(education.getContent()).isEqualTo(content);
        assertThat(education.getStartDate()).isEqualTo(startDate);
        assertThat(education.getEndDate()).isEqualTo(endDate);
    }

}
