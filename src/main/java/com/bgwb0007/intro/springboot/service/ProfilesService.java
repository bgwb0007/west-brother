package com.bgwb0007.intro.springboot.service;

import com.bgwb0007.intro.springboot.domain.profiles.ProfilesRepository;
import com.bgwb0007.intro.springboot.web.dto.ProfilesListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProfilesService {
    private final ProfilesRepository profilesRepository;
    @Transactional
    public List<ProfilesListResponseDto> findAll(){
        return profilesRepository.findAll().stream()
                .map(ProfilesListResponseDto::new)
                .collect(Collectors.toList());
    }
}
