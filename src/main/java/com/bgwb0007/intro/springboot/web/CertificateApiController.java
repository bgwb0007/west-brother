package com.bgwb0007.intro.springboot.web;

import com.bgwb0007.intro.springboot.service.CertificateService;
import com.bgwb0007.intro.springboot.web.dto.CertificateSaveRequestDto;
import com.bgwb0007.intro.springboot.web.dto.CertificateUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class CertificateApiController {
    private final CertificateService certificateService;

    @PostMapping("/api/v1/certificate")
    public Long save(@RequestBody CertificateSaveRequestDto requestDto){
        return certificateService.save(requestDto);
    }
    @GetMapping("/api/v1/certificate")
    public List<Map> findAllForHtml(){
        return certificateService.findAllForHtml();
    }
    @GetMapping("/api/v1/certificate/{id}")
    public Map findById(@PathVariable Long id){
        return certificateService.findById(id);
    }
    @PutMapping("/api/v1/certificate/{id}")
    public Long update(@PathVariable Long id, @RequestBody CertificateUpdateRequestDto requestDto){
        return certificateService.update(id,requestDto);
    }
    @DeleteMapping("/api/v1/certificate/{id}")
    public Long delete(@PathVariable Long id){
        return certificateService.delete(id);
    }
}
