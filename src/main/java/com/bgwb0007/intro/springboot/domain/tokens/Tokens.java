package com.bgwb0007.intro.springboot.domain.tokens;

import com.bgwb0007.intro.springboot.domain.BaseTimeEntity;
import com.bgwb0007.intro.springboot.web.dto.TokensUpdateRequestDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
public class Tokens extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String url;
    private String accessToken;
    private String expirationPeriod;

    @Builder
    public Tokens(String name, String url, String accessToken, String expirationPeriod) {
        this.name = name;
        this.url = url;
        this.accessToken = accessToken;
        this.expirationPeriod = expirationPeriod;
    }
    public void update(TokensUpdateRequestDto requestDto){
        this.name = requestDto.getName();
        this.url = requestDto.getUrl();
        this.accessToken = requestDto.getAccessToken();
        this.expirationPeriod = requestDto.getExpirationPeriod();
    }
}
