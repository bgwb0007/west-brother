package com.bgwb0007.intro.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class IndexResponseDto {

    private final String name;
    private final int amount;
}
