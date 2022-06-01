package com.bgwb0007.intro.springboot.domain.resume;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class Career extends Resume {
    @Column(nullable = false)
    private LocalDate startDate;    // 입사일자

    private LocalDate endDate;      // 퇴사일자
}
