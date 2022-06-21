package com.bgwb0007.intro.springboot.domain.resume;

import com.bgwb0007.intro.springboot.web.dto.CertificateSaveRequestDto;
import com.bgwb0007.intro.springboot.web.dto.CertificateUpdateRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
@Entity
public class Certificate extends Resume {
    @Column(nullable = false)
    private String certification;   // 인증기관
    @Column(nullable = false)
    private LocalDate licenseDate;  // 취득일자

    private LocalDate endDate;  // 종료일자

    private String imageFileName;

    private String imagePath;

    public Certificate(CertificateSaveRequestDto requestDto) {
        super(requestDto.getTitle(), requestDto.getContent());
        this.certification = requestDto.getCertification();
        LocalDate licenseDate = requestDto.getLicenseDate().equals("") ? null : LocalDate.parse(requestDto.getLicenseDate());
        LocalDate eDate = requestDto.getEndDate().equals("") ? null: LocalDate.parse(requestDto.getEndDate());
        this.licenseDate = licenseDate;
        this.endDate = eDate;
        this.imageFileName = requestDto.getImageFileName();
        this.imagePath = requestDto.getImagePath();
    }
    public void update(CertificateUpdateRequestDto requestDto){
        this.setTitle(requestDto.getTitle());
        this.setContent(requestDto.getContent());
        this.certification = requestDto.getCertification();
        LocalDate licenseDate = requestDto.getLicenseDate().equals("") ? null : LocalDate.parse(requestDto.getLicenseDate());
        LocalDate eDate = requestDto.getEndDate().equals("") ? null: LocalDate.parse(requestDto.getEndDate());
        this.licenseDate = licenseDate;
        this.endDate = eDate;
        this.imageFileName = requestDto.getImageFileName();
        this.imagePath = requestDto.getImagePath();
    }
    public Map<String ,String> toMapForList(){
        HashMap<String, String> retMap = new HashMap<>();
        retMap.put("id", String.valueOf(this.getId()));
        retMap.put("title", this.getTitle());
        retMap.put("content", this.getContent());
        retMap.put("certification", this.certification);
        retMap.put("licenseDate", String.valueOf(this.licenseDate));
        retMap.put("endDate", String.valueOf(this.endDate));
        retMap.put("imageFileName", this.imageFileName);
        retMap.put("imagePath", this.imagePath);
        return retMap;
    }
    public Map<String ,String> toMapForOne(){
        HashMap<String, String> retMap = new HashMap<>();
        retMap.put("id", String.valueOf(this.getId()));
        retMap.put("title", this.getTitle());
        retMap.put("content", this.getContent());
        retMap.put("licenseDate", String.valueOf(this.licenseDate));
        retMap.put("endDate", String.valueOf(this.endDate));
        retMap.put("createdDate", String.valueOf(this.getCreatedDate()));
        retMap.put("modifiedDate", String.valueOf(this.getModifiedDate()));
        //TODO. dType 처리해서 넘겨주기
//        retMap.put("dType", this.getDType());
        return retMap;
    }
}
