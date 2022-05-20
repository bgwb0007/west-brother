package com.bgwb0007.intro.springboot.web;

import com.bgwb0007.intro.springboot.service.ProfilesService;
import com.bgwb0007.intro.springboot.web.dto.ProfilesListResponseDto;
import com.bgwb0007.intro.springboot.web.dto.ProfilesUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProfilesApiController {

    private final ProfilesService profilesService;

    @GetMapping("/api/v1/profiles")
    public List<ProfilesListResponseDto> findAll(){ return profilesService.findAll();
    }
    @PutMapping("/api/v1/profiles/{id}")
    public Long update(@PathVariable Long id, @RequestBody ProfilesUpdateRequestDto requestDto){
        return profilesService.update(id,requestDto);
    }
}
