package com.bgwb0007.intro.springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ResumeService {
    private final CareerService careerService;
    private final EducationService educationService;
    private final CertificateService certificateService;
    private final ProjectService projectService;

    public Map findAll(){
        Map retMap = new HashMap<>();
        retMap.put("careers", careerService.findAllForHtml());
        retMap.put("educations", educationService.findAllForHtml());
        retMap.put("certificates", certificateService.findAllForHtml());
        retMap.put("projects", projectService.findAllForHtml());
        return retMap;
    }

}
