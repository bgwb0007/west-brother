package com.bgwb0007.intro.springboot.domain.resume;

import com.bgwb0007.intro.springboot.web.dto.CareerUpdateRequestDto;
import com.bgwb0007.intro.springboot.web.dto.EducationSaveRequestDto;
import com.bgwb0007.intro.springboot.web.dto.EducationUpdateRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
@Entity
public class Education extends Resume {
    @Column(nullable = false)
    private LocalDate startDate;
    private LocalDate endDate;

    public Education(EducationSaveRequestDto requestDto) {
        super(requestDto.getTitle(), requestDto.getContent());
        LocalDate sDate = requestDto.getStartDate().equals("") ? null : LocalDate.parse(requestDto.getStartDate());
        LocalDate eDate = requestDto.getEndDate().equals("") ? null: LocalDate.parse(requestDto.getEndDate());
        this.startDate = sDate;
        this.endDate = eDate;
    }
    public void update(EducationUpdateRequestDto requestDto){
        this.setTitle(requestDto.getTitle());
        this.setContent(requestDto.getContent());
        LocalDate sDate = requestDto.getStartDate().equals("") ? null : LocalDate.parse(requestDto.getStartDate());
        LocalDate eDate = requestDto.getEndDate().equals("") ? null: LocalDate.parse(requestDto.getEndDate());
        this.startDate = sDate;
        this.endDate = eDate;
    }
    public Map<String ,String> toMapForList(){
        HashMap<String, String> retMap = new HashMap<>();
        retMap.put("id", String.valueOf(this.getId()));
        retMap.put("title", this.getTitle());
        retMap.put("content", this.getContent());
        retMap.put("startDate", String.valueOf(this.startDate));
        retMap.put("endDate", String.valueOf(this.endDate));
        return retMap;
    }
    public Map<String ,String> toMapForOne(){
        HashMap<String, String> retMap = new HashMap<>();
        retMap.put("id", String.valueOf(this.getId()));
        retMap.put("title", this.getTitle());
        retMap.put("content", this.getContent());
        retMap.put("startDate", String.valueOf(this.startDate));
        retMap.put("endDate", String.valueOf(this.endDate));
        retMap.put("createdDate", String.valueOf(this.getCreatedDate()));
        retMap.put("modifiedDate", String.valueOf(this.getModifiedDate()));
        //TODO. dType 처리해서 넘겨주기
//        retMap.put("dType", this.getDType());
        return retMap;
    }
}
