package com.bgwb0007.intro.springboot.web;

import com.bgwb0007.intro.springboot.service.ProjectService;
import com.bgwb0007.intro.springboot.web.dto.CertificateSaveRequestDto;
import com.bgwb0007.intro.springboot.web.dto.CertificateUpdateRequestDto;
import com.bgwb0007.intro.springboot.web.dto.ProjectSaveRequestDto;
import com.bgwb0007.intro.springboot.web.dto.ProjectUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ProjectApiController {
    private final ProjectService projectService;

    @PostMapping("/api/v1/project")
    public Long save(@RequestBody ProjectSaveRequestDto requestDto){
        return projectService.save(requestDto);
    }
    @GetMapping("/api/v1/project")
    public List<Map> findAllForHtml(){
        return projectService.findAllForHtml();
    }
    @GetMapping("/api/v1/project/{id}")
    public Map findById(@PathVariable Long id){
        return projectService.findById(id);
    }
    @PutMapping("/api/v1/project/{id}")
    public Long update(@PathVariable Long id, @RequestBody ProjectUpdateRequestDto requestDto){
        return projectService.update(id,requestDto);
    }
    @DeleteMapping("/api/v1/project/{id}")
    public Long delete(@PathVariable Long id){
        return projectService.delete(id);
    }
}
