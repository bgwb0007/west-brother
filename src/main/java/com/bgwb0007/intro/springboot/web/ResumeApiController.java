package com.bgwb0007.intro.springboot.web;

import com.bgwb0007.intro.springboot.service.ResumeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ResumeApiController {
    private final ResumeService resumeService;

    @GetMapping("/api/v1/resume")
    public Map findAllResumeInfo() throws JsonProcessingException {
        return resumeService.findAll();
    }

}
