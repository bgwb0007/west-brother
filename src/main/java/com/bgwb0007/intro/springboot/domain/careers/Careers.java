package com.bgwb0007.intro.springboot.domain.careers;

import com.bgwb0007.intro.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
public class Careers extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 250)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;

    private Date startDate;
    private Date endDate;
    @Column(length=1,nullable = false)
    private String careerGubun;

    @Builder
    public Careers(String title, String content, Date startDate, Date endDate, String careerGubun) {
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.careerGubun = careerGubun;
    }
}
