package com.bgwb0007.intro.springboot.domain.resume;

import com.bgwb0007.intro.springboot.util.StringUtil;
import com.bgwb0007.intro.springboot.web.dto.CareerUpdateRequestDto;
import com.bgwb0007.intro.springboot.web.dto.EducationSaveRequestDto;
import com.bgwb0007.intro.springboot.web.dto.EducationUpdateRequestDto;
import lombok.Builder;
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
    private String ext1;            // 예비컬럼1
    private String ext2;            // 예비컬럼2
    private String ext3;            // 예비컬럼3
    @Builder
    public Education(String title, String content, LocalDate startDate, LocalDate endDate) {
        super(title, content);
        this.startDate = startDate;
        this.endDate = endDate;
    }

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
        retMap.put("id", StringUtil.nvl(this.getId()));
        retMap.put("title", StringUtil.nvl(this.getTitle()));
        retMap.put("content", StringUtil.nvl(this.getContent()));
        retMap.put("startDate", StringUtil.nvl(this.startDate));
        retMap.put("endDate", StringUtil.nvl(this.endDate));
        String[] st = StringUtil.nvl(this.startDate).split("-");
        String[] ed = StringUtil.nvl(this.endDate).split("-");
        retMap.put("startDate2",st[0]+"."+st[1]);
        retMap.put("endDate2",ed.length > 1 ? ed[0]+"."+ed[1] : "현재");
        return retMap;
    }
    public Map<String ,String> toMapForOne(){
        HashMap<String, String> retMap = new HashMap<>();
        retMap.put("id", StringUtil.nvl(this.getId()));
        retMap.put("title", StringUtil.nvl(this.getTitle()));
        retMap.put("content", StringUtil.nvl(this.getContent()));
        retMap.put("startDate", StringUtil.nvl(this.startDate));
        retMap.put("endDate", StringUtil.nvl(this.endDate));
        retMap.put("createdDate", StringUtil.nvl(this.getCreatedDate()));
        retMap.put("modifiedDate", StringUtil.nvl(this.getModifiedDate()));
        //TODO. dType 처리해서 넘겨주기
//        retMap.put("dType", this.getDType());
        return retMap;
    }
}
