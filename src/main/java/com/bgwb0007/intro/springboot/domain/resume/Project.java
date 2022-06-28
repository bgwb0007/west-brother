package com.bgwb0007.intro.springboot.domain.resume;

import com.bgwb0007.intro.springboot.util.StringUtil;
import com.bgwb0007.intro.springboot.web.dto.EducationUpdateRequestDto;
import com.bgwb0007.intro.springboot.web.dto.ProjectSaveRequestDto;
import com.bgwb0007.intro.springboot.web.dto.ProjectUpdateRequestDto;
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
public class Project extends Resume {
    @Column
    private String contentDetail;
    @Column(nullable = false)
    private LocalDate startDate;
    private LocalDate endDate;

    private String ext1;            // 예비컬럼1
    private String ext2;            // 예비컬럼2
    private String ext3;            // 예비컬럼3
    @Builder
    public Project(String title, String content, String contentDetail, LocalDate startDate, LocalDate endDate) {
        super(title, content);
        this.contentDetail = contentDetail;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Project(ProjectSaveRequestDto requestDto) {
        super(requestDto.getTitle(), requestDto.getContent());
        this.contentDetail = requestDto.getContentDetail();
        LocalDate sDate = requestDto.getStartDate().equals("") ? null : LocalDate.parse(requestDto.getStartDate());
        LocalDate eDate = requestDto.getEndDate().equals("") ? null: LocalDate.parse(requestDto.getEndDate());
        this.startDate = sDate;
        this.endDate = eDate;
    }
    public void update(ProjectUpdateRequestDto requestDto){
        this.setTitle(requestDto.getTitle());
        this.setContent(requestDto.getContent());
        this.contentDetail = requestDto.getContentDetail();
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
        retMap.put("contentDetail", StringUtil.nvl(this.contentDetail));
        retMap.put("startDate", StringUtil.nvl(this.startDate));
        retMap.put("endDate", StringUtil.nvl(this.endDate,"현재"));
        return retMap;
    }
    public Map<String ,String> toMapForOne(){
        HashMap<String, String> retMap = new HashMap<>();
        retMap.put("id", StringUtil.nvl(this.getId()));
        retMap.put("title", StringUtil.nvl(this.getTitle()));
        retMap.put("content", StringUtil.nvl(this.getContent()));
        retMap.put("contentDetail", StringUtil.nvl(this.contentDetail));
        retMap.put("startDate", StringUtil.nvl(this.startDate));
        retMap.put("endDate", StringUtil.nvl(this.endDate));
        retMap.put("createdDate", StringUtil.nvl(this.getCreatedDate()));
        retMap.put("modifiedDate", StringUtil.nvl(this.getModifiedDate()));
        //TODO. dType 처리해서 넘겨주기
//        retMap.put("dType", this.getDType());
        return retMap;
    }
}
