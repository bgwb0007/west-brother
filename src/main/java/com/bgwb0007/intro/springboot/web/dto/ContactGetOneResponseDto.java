package com.bgwb0007.intro.springboot.web.dto;

import com.bgwb0007.intro.springboot.domain.contact.Contact;
import com.bgwb0007.intro.springboot.domain.profiles.Profiles;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class ContactGetOneResponseDto {
    private Long id;
    private String name;
    private String engName;
    private String logoFileName;
    private String logoPath;
    private String siteId;
    private String siteUrl;
    private Integer sortOrder;
    private Profiles profiles;
    private String pageGubun;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public ContactGetOneResponseDto(Contact contact) {
        this.id = contact.getId();
        this.name = contact.getName();
        this.engName = contact.getEngName();
        this.logoFileName = contact.getLogoFileName();
        this.logoPath = contact.getLogoPath();
        this.siteId = contact.getSiteId();
        this.siteUrl = contact.getSiteUrl();
        this.sortOrder = contact.getSortOrder();
        this.profiles = contact.getProfiles();
        this.pageGubun = contact.getProfiles().getPageGubun();
        this.createdDate = contact.getCreatedDate();
        this.modifiedDate = contact.getModifiedDate();
    }
}
