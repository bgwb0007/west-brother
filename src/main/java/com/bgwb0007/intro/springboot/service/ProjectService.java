package com.bgwb0007.intro.springboot.service;

import com.bgwb0007.intro.springboot.domain.resume.Project;
import com.bgwb0007.intro.springboot.domain.resume.ProjectRepository;
import com.bgwb0007.intro.springboot.web.dto.ProjectSaveRequestDto;
import com.bgwb0007.intro.springboot.web.dto.ProjectUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Transactional
    public Long save(ProjectSaveRequestDto requestDto){
        return projectRepository.save(new Project(requestDto)).getId();
    }
    public List<Map> findAllForHtml(){
        return projectRepository.findAllOrderByStartDateAsc().stream()
                .map(Project::toMapForList)
                .collect(Collectors.toList());
    }
    public Map findById(Long id){
        Project project = projectRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 Project가 없습니다. id="+id));
        return project.toMapForOne();
    }
    @Transactional
    public Long update(Long id, ProjectUpdateRequestDto requestDto){
        Project project = projectRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 Project가 없습니다. id="+id));
        project.update(requestDto);
        return id;
    }
    @Transactional
    public Long delete(Long id){
        Project project = projectRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 Project가 없습니다. id="+id));
        projectRepository.delete(project);
        return id;
    }

}
