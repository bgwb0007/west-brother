package com.bgwb0007.intro.springboot.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class TokensUpdateRequestDto {
    private String name;
    private String url;
    private String accessToken;
    private String expirationPeriod;

}
