package com.bgwb0007.intro.springboot.domain.contact;

import com.bgwb0007.intro.springboot.domain.BaseTimeEntity;
import com.bgwb0007.intro.springboot.domain.profiles.Profiles;
import com.bgwb0007.intro.springboot.web.dto.ContactSaveRequestDto;
import com.bgwb0007.intro.springboot.web.dto.ContactUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Contact extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 100)
    private String engName;
    @Column(length = 100)
    private String logoFileName;
    @Column(length = 500)
    private String logoHtml;
    @Column(length = 250)
    private String siteId;
    @Column(length = 250)
    private String siteUrl;
    @Column(length = 100)
    private Integer sortOrder;

    @ManyToOne @JoinColumn(name = "profilesId")
    private Profiles profiles;

    @Builder
    public Contact(String name, String engName, String logoHtml, String logoFileName, String siteId, String siteUrl, Integer sortOrder, Profiles profiles) {
        this.name = name;
        this.engName = engName;
        this.logoHtml = logoHtml;
        this.logoFileName = logoFileName;
        this.siteId = siteId;
        this.siteUrl = siteUrl;
        this.sortOrder = sortOrder;
        this.profiles = profiles;
    }
    public Contact(ContactSaveRequestDto requestDto, Profiles profiles){
        this.name = requestDto.getName();
        this.engName = requestDto.getEngName();
        this.logoHtml = requestDto.getLogoHtml();
        this.logoFileName = requestDto.getLogoFileName();
        this.siteId = requestDto.getSiteId();
        this.siteUrl = requestDto.getSiteUrl();
        this.sortOrder = requestDto.getSortOrder();
        this.profiles = profiles;
    }

    public void update(ContactUpdateRequestDto requestDto){
        this.name = requestDto.getName();
        this.engName = requestDto.getEngName();
        this.logoFileName = requestDto.getLogoFileName();
        this.logoHtml = requestDto.getLogoHtml();
        this.siteId = requestDto.getSiteId();
        this.siteUrl = requestDto.getSiteUrl();
        this.sortOrder = requestDto.getSortOrder();

//        profiles.getContactList().add(this);
    }
}
