package com.bgwb0007.intro.springboot.service;

import com.bgwb0007.intro.springboot.domain.tokens.Tokens;
import com.bgwb0007.intro.springboot.domain.tokens.TokensRepository;
import com.bgwb0007.intro.springboot.web.dto.TokensListResponseDto;
import com.bgwb0007.intro.springboot.web.dto.TokensSaveRequestDto;
import com.bgwb0007.intro.springboot.web.dto.TokensUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokensService {
    private final TokensRepository tokensRepository;
    public Long save(TokensSaveRequestDto requestDto){
        return tokensRepository.save(requestDto.toEntity()).getId();
    }
    public Long update(Long id, TokensUpdateRequestDto requestDto){
        Tokens tokens = tokensRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 토큰 데이터가 없습니다. id="+id));
        tokens.update(requestDto);
        return id;
    }
    public List findAll(){
        return tokensRepository.findAll().stream()
                .map(TokensListResponseDto::new)
                .collect(Collectors.toList());
    }
    public Tokens findByName(String name){
        return tokensRepository.findByName(name)
                .orElseThrow(()->new IllegalArgumentException("해당 토큰 데이터가 없습니다. name="+name));
    }
}
