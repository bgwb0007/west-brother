package com.bgwb0007.intro.springboot.web.dto;

import com.bgwb0007.intro.springboot.domain.contact.Contact;
import com.bgwb0007.intro.springboot.domain.profiles.Profiles;

import java.util.List;

public class ProfilesListResponseDto {
    private String name;
    private String content1;
    private String content2;
    private String photoFileName;
    private String photoPath;
    private String pageGubun;
    private List<Contact> contactList;

    public ProfilesListResponseDto(Profiles profiles) {
        this.name = profiles.getName();
        this.content1 = profiles.getContent1();
        this.content2 = profiles.getContent2();
        this.photoFileName = profiles.getPhotoFileName();
        this.photoPath = profiles.getPhotoPath();
        this.pageGubun = profiles.getPageGubun();
        this.contactList = profiles.getContactList();
    }
}
