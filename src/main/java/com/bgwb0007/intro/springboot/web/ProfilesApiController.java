package com.bgwb0007.intro.springboot.web;

import com.bgwb0007.intro.springboot.service.ProfilesService;
import com.bgwb0007.intro.springboot.web.dto.ProfilesListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProfilesApiController {

    private final ProfilesService profilesService;

    @GetMapping("/api/v1/profiles")
    public List<ProfilesListResponseDto> findAllDesc(){ return profilesService.findAll();
    }
}
