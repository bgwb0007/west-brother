package com.bgwb0007.intro.springboot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public Map findAll() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map retMap = new HashMap<>();

        retMap.put("careers", careerService.findAllForHtml());
        retMap.put("educations", educationService.findAllForHtml());
        retMap.put("certificates", certificateService.findAllForHtml());
        retMap.put("projects", projectService.findAllForHtml());
        retMap.put("projectsJson", mapper.writeValueAsString(projectService.findAllForHtml()));
        retMap.put("size", careerService.findAllForHtml().size() + educationService.findAllForHtml().size() +
                certificateService.findAllForHtml().size() + projectService.findAllForHtml().size());
        return retMap;
    }

}
