package com.bgwb0007.intro.springboot.domain.profiles;

import com.bgwb0007.intro.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Profiles extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 100, nullable = false)
    private String instagram;
    @Column(length = 100, nullable = false)
    private String linkedIn;
    @Column(length = 100, nullable = false)
    private String gitHub;
    @Column(length = 100, nullable = false)
    private String email;
    @Column(columnDefinition = "TEXT")
    private String content;

    @Builder
    public Profiles(String name, String instagram, String linkedIn, String gitHub, String email, String content) {
        this.name = name;
        this.instagram = instagram;
        this.linkedIn = linkedIn;
        this.gitHub = gitHub;
        this.email = email;
        this.content = content;
    }
}
