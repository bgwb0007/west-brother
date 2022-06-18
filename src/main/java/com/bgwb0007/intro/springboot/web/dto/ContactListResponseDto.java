package com.bgwb0007.intro.springboot.web.dto;

import com.bgwb0007.intro.springboot.domain.contact.Contact;
import com.bgwb0007.intro.springboot.domain.profiles.Profiles;
import lombok.Getter;

import javax.persistence.Column;
import java.util.HashMap;
import java.util.Map;

public class ContactListResponseDto {
    private Long id;
    private String name;
    private String engName;
    private String logoFileName;
    private String logoHtml;
    private String siteId;
    private String siteUrl;
    private Integer sortOrder;
    private Profiles profiles;
    private String pageGubun;


    public ContactListResponseDto(Contact contact) {
        this.id = contact.getId();
        this.name = contact.getName();
        this.engName = contact.getEngName();
        this.logoFileName = contact.getLogoFileName();
        this.logoHtml = contact.getLogoHtml();
        this.siteId = contact.getSiteId();
        this.siteUrl = contact.getSiteUrl();
        this.sortOrder = contact.getSortOrder();
        this.profiles =  contact.getProfiles();
        this.pageGubun = this.profiles.getPageGubun();
    }

    public Map<String, String> toMap(){
        HashMap<String, String> retMap = new HashMap<>();
        retMap.put("id", String.valueOf(this.id));
        retMap.put("name", this.name);
        retMap.put("engName", this.engName);
        retMap.put("logoFileName", this.logoFileName);
        retMap.put("logoHtml", this.logoHtml);
        retMap.put("sortOrder",String.valueOf(this.sortOrder));
        retMap.put("siteId", this.siteId);
        retMap.put("siteUrl", this.siteUrl);
        retMap.put("profilesId", String.valueOf(this.profiles.getId()));
        retMap.put("pageGubun", this.profiles.getPageGubun());
        return retMap;
    }
}
