package com.bgwb0007.intro.springboot.domain.profiles;

import com.bgwb0007.intro.springboot.domain.BaseTimeEntity;
import com.bgwb0007.intro.springboot.domain.contact.Contact;
import com.bgwb0007.intro.springboot.web.dto.ProfilesUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Profiles extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String content1;
    @Column(columnDefinition = "TEXT")
    private String content2;
    @Column
    private String photoFileName;
    @Column
    private String photoPath;
    @Column
    private String pageGubun;

    @OneToMany(mappedBy = "profiles")
    private List<Contact> contactList = new ArrayList<>();

    @Builder
    public Profiles(String name, String content1, String content2, String photoFileName, String photoPath, String pageGubun, List<Contact> contactList) {
        this.name = name;
        this.content1 = content1;
        this.content2 = content2;
        this.photoFileName = photoFileName;
        this.photoPath = photoPath;
        this.pageGubun = pageGubun;
        this.contactList = contactList;
    }

    public void update(ProfilesUpdateRequestDto requestDto){
        this.name = requestDto.getName();
        this.content1 = requestDto.getContent1();
        this.content2 = requestDto.getContent2();
        this.photoFileName = requestDto.getPhotoFileName();
        this.photoPath = requestDto.getPhotoPath();
        this.pageGubun = requestDto.getPageGubun();
    }
}
