package com.bgwb0007.intro.springboot.web.dto;

import com.bgwb0007.intro.springboot.domain.contact.Contact;
import com.bgwb0007.intro.springboot.domain.profiles.Profiles;

import java.util.List;

public class ProfilesListResponseDto {
    private String name;
    private String content;
    private String photoFileName;
    private String photoPath;
    private List<Contact> contactList;

    public ProfilesListResponseDto(Profiles profiles) {
        this.name = profiles.getName();
        this.content = profiles.getContent();
        this.photoFileName = profiles.getPhotoFileName();
        this.photoPath = profiles.getPhotoPath();
        this.contactList = profiles.getContactList();
    }
}
