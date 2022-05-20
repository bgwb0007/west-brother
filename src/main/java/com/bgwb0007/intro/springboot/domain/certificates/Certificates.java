package com.bgwb0007.intro.springboot.domain.certificates;

import com.bgwb0007.intro.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
public class Certificates extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 250, nullable = false)
    private String title;
    @Column
    private String certification;
    @Column(nullable = false)
    private Date licenseDate;

    private String image;

    @Builder
    public Certificates(String title, String certification, Date licenseDate, String image) {
        this.title = title;
        this.certification = certification;
        this.licenseDate = licenseDate;
        this.image = image;
    }
}
