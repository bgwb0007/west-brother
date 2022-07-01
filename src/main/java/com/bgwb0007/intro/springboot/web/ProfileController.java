package com.bgwb0007.intro.springboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProfileController {
    private final Environment env;

    @GetMapping("/profile")
    public String profile() {
        List<String> profileList = Arrays.asList(env.getActiveProfiles());
        List<String> realProfileList = Arrays.asList("real", "real1", "real2");
        String defaultProfile = profileList.isEmpty() ? "default" : profileList.get(0);
        return profileList.stream().filter(realProfileList::contains).findAny().orElse(defaultProfile);
    }
}
