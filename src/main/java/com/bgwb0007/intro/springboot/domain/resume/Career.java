package com.bgwb0007.intro.springboot.domain.resume;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class Career extends Resume {
    @Column(nullable = false)
    private LocalDate startDate;    // 입사일자

    private LocalDate endDate;      // 퇴사일자

    public Career(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Builder
    public Career(String title, String content, LocalDate startDate, LocalDate endDate) {
        super(title, content);
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
