package com.bgwb0007.intro.springboot.domain.resume;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class Education extends Resume {
    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;
}
