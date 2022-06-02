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
    private String content;
    @Column
    private String photoFileName;
    @Column
    private String photoPath;

    @OneToMany(mappedBy = "profiles")
    private List<Contact> contactList = new ArrayList<>();

    @Builder
    public Profiles(String name, String content, String photoFileName, String photoPath, List<Contact> contactList) {
        this.name = name;
        this.content = content;
        this.photoFileName = photoFileName;
        this.photoPath = photoPath;
        this.contactList = contactList;
    }

    public void update(ProfilesUpdateRequestDto requestDto){
        this.name = requestDto.getName();
        this.content = requestDto.getContent();
        this.photoFileName = requestDto.getPhotoFileName();
        this.photoPath = requestDto.getPhotoPath();
    }
}
