package com.bgwb0007.intro.springboot.web.dto;

import com.bgwb0007.intro.springboot.domain.tokens.Tokens;
import com.bgwb0007.intro.springboot.util.StringUtil;

import java.time.LocalDate;

public class TokensListResponseDto {
    private Long id;
    private String name;
    private String url;
    private String accessToken;
    private String expirationPeriod;
    private String createdDate;
    private String modifiedDate;

    public TokensListResponseDto(Tokens tokens) {
        this.id = tokens.getId();
        this.name = StringUtil.nvl(tokens.getName());
        this.url = StringUtil.nvl(tokens.getUrl());
        this.accessToken = StringUtil.nvl(tokens.getAccessToken());
        this.expirationPeriod = StringUtil.nvl(tokens.getExpirationPeriod());
        this.createdDate = StringUtil.nvl(tokens.getCreatedDate());
        this.modifiedDate = StringUtil.nvl(tokens.getModifiedDate());
    }
}
