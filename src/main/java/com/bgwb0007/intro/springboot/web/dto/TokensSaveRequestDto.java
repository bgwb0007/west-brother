package com.bgwb0007.intro.springboot.web.dto;

import com.bgwb0007.intro.springboot.domain.tokens.Tokens;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokensSaveRequestDto {
    private String name;
    private String url;
    private String accessToken;
    private String expirationPeriod;

    public Tokens toEntity() {
        return Tokens.builder()
                .name(name)
                .url(url)
                .accessToken(accessToken)
                .expirationPeriod(expirationPeriod)
                .build();
    }
    @Builder
    public TokensSaveRequestDto(String name, String url, String accessToken, String expirationPeriod) {
        this.name = name;
        this.url = url;
        this.accessToken = accessToken;
        this.expirationPeriod = expirationPeriod;
    }
}
