package com.bgwb0007.intro.springboot.domain.resume;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class Certificate extends Resume {
    @Column(nullable = false)
    private String certification;   // 인증기관
    @Column(nullable = false)
    private LocalDate licenseDate;  // 취득일자

    private String imageFileName;

    private String imagePath;

}
