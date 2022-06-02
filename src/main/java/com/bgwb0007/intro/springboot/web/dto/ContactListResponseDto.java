package com.bgwb0007.intro.springboot.web.dto;

import com.bgwb0007.intro.springboot.domain.contact.Contact;

import javax.persistence.Column;

public class ContactListResponseDto {
    private String name;
    private String engName;
    private String logoFileName;
    private String logoPath;
    private String siteId;
    private String siteUrl;
    private Integer sortOrder;

    public ContactListResponseDto(Contact contact) {
        this.name = contact.getName();
        this.engName = contact.getEngName();
        this.logoFileName = contact.getLogoFileName();
        this.logoPath = contact.getLogoPath();
        this.siteId = contact.getSiteId();
        this.siteUrl = contact.getSiteUrl();
        this.sortOrder = contact.getSortOrder();
    }
}
