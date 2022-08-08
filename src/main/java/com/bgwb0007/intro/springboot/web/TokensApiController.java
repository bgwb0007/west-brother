package com.bgwb0007.intro.springboot.web;

import com.bgwb0007.intro.springboot.service.TokensService;
import com.bgwb0007.intro.springboot.web.dto.TokensSaveRequestDto;
import com.bgwb0007.intro.springboot.web.dto.TokensUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class TokensApiController {
    private final TokensService tokensService;

    @PostMapping("/api/v1/tokens")
    public Long save(@RequestBody TokensSaveRequestDto requestDto){
        return tokensService.save(requestDto);
    }
    @PutMapping("/api/v1/tokens/{id}")
    public Long update(@PathVariable Long id, @RequestBody TokensUpdateRequestDto requestDto){
        return tokensService.update(id,requestDto);
    }
}
