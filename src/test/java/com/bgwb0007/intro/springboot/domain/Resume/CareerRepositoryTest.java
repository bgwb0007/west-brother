package com.bgwb0007.intro.springboot.domain.Resume;

import com.bgwb0007.intro.springboot.domain.resume.Career;
import com.bgwb0007.intro.springboot.domain.resume.CareerRepository;
import com.bgwb0007.intro.springboot.web.dto.CareerSaveRequestDto;
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
public class CareerRepositoryTest {
    @Autowired
    CareerRepository careerRepository;

    @After
    public void cleanup(){careerRepository.deleteAll();}

    @Test
    public void 경력등록_하고_가져오기_endDate_null() throws Exception {
        //given
        String title = "LG CNS";
        String content = "클라우드개발3팀";
        LocalDate startDate = LocalDate.of(2021,07,19);
        LocalDate endDate = null;

        //when
        careerRepository.save(Career.builder()
                .title(title)
                .content(content)
                .startDate(startDate)
                .endDate(endDate)
                .build());
        List<Career> careerList = careerRepository.findAll();

        //then
        Career career = careerList.get(0);
        assertThat(career.getTitle()).isEqualTo(title);
        assertThat(career.getContent()).isEqualTo(content);
        assertThat(career.getStartDate()).isEqualTo(startDate);
        assertThat(career.getEndDate()).isEqualTo(endDate);
    }

}
