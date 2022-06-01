package com.bgwb0007.intro.springboot.domain.resume;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class Project extends Resume {
    @Column
    private String contentsDetail;
    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;
}
