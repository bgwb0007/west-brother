package com.bgwb0007.intro.springboot.domain.resume;

import com.bgwb0007.intro.springboot.domain.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn  // DTYPE 자동 생성 : 어떤 테이블에서 생성되었는지 알려줌.
public class Resume extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 250, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

//    private String DType;       // 자동 저장되는 데이터 타입
//    private String ext1;        // 예비 컬럼1
//    private String ext2;        // 예비 컬럼2
//    private String ext3;        // 예비 컬럼3

    public Resume(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
