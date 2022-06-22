package com.bgwb0007.intro.springboot.domain.Resume;

import com.bgwb0007.intro.springboot.domain.resume.Project;
import com.bgwb0007.intro.springboot.domain.resume.ProjectRepository;
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
public class ProjectRepositoryTest {
    @Autowired
    ProjectRepository profilesRepository;

    @After
    public void cleanup(){profilesRepository.deleteAll();}

    @Test
    public void 프로젝트등록_하고_가져오기_endDate_null() throws Exception {
        //given
        String title = "KB국민카드";
        String content = "홈 리뉴얼 프로젝트";
        String contentDetail = "한국산업진흥원";
        LocalDate startDate = LocalDate.of(2021,9,5);
        LocalDate endDate = null;

        //when
        profilesRepository.save(Project.builder()
                .title(title)
                .content(content)
                .contentDetail(contentDetail)
                .startDate(startDate)
                .endDate(endDate)
                .build());
        List<Project> projectList = profilesRepository.findAll();

        //then
        Project project = projectList.get(0);
        assertThat(project.getTitle()).isEqualTo(title);
        assertThat(project.getContent()).isEqualTo(content);
        assertThat(project.getContentDetail()).isEqualTo(contentDetail);
        assertThat(project.getStartDate()).isEqualTo(startDate);
        assertThat(project.getEndDate()).isEqualTo(endDate);
    }

}
