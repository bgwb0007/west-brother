package com.bgwb0007.intro.springboot.web.dto;

import com.bgwb0007.intro.springboot.domain.contact.Contact;
import com.bgwb0007.intro.springboot.domain.profiles.Profiles;
import lombok.Getter;

import javax.persistence.Column;

public class ContactListResponseDto {
    private Long id;
    private String name;
    private String engName;
    private String logoFileName;
    private String logoPath;
    private String siteId;
    private String siteUrl;
    private Integer sortOrder;
    private Profiles profiles;
    private String pageGUbun;

    public void setPageGUbun(String pageGUbun){
        this.pageGUbun = pageGUbun;
    }
    public Profiles getProfiles(){
        return this.profiles;
    }

    public ContactListResponseDto(Contact contact) {
        this.id = contact.getId();
        this.name = contact.getName();
        this.engName = contact.getEngName();
        this.logoFileName = contact.getLogoFileName();
        this.logoPath = contact.getLogoPath();
        this.siteId = contact.getSiteId();
        this.siteUrl = contact.getSiteUrl();
        this.sortOrder = contact.getSortOrder();
        this.profiles =  contact.getProfiles();
    }
}
