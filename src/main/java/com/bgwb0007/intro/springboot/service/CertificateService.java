package com.bgwb0007.intro.springboot.service;

import com.bgwb0007.intro.springboot.domain.resume.Certificate;
import com.bgwb0007.intro.springboot.domain.resume.CertificateRepository;
import com.bgwb0007.intro.springboot.web.dto.CertificateSaveRequestDto;
import com.bgwb0007.intro.springboot.web.dto.CertificateUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CertificateService {
    private final CertificateRepository certificateRepository;

    @Transactional
    public Long save(CertificateSaveRequestDto requestDto){
        return certificateRepository.save(new Certificate(requestDto)).getId();
    }
    public List<Map> findAllForHtml(){
        return certificateRepository.findAllOrderByLicenseDateDesc().stream()
                .map(Certificate::toMapForList)
                .collect(Collectors.toList());
    }
    public Map findById(Long id){
        Certificate certificate = certificateRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 자격증이 없습니다. id="+id));
        return certificate.toMapForOne();
    }
    @Transactional
    public Long update(Long id, CertificateUpdateRequestDto requestDto){
        Certificate certificate = certificateRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 자격증이 없습니다. id="+id));
        certificate.update(requestDto);
        return id;
    }
    @Transactional
    public Long delete(Long id){
        Certificate certificate = certificateRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 자격증이 없습니다. id="+id));
        certificateRepository.delete(certificate);
        return id;
    }

}
