package com.bgwb0007.intro.springboot.web.dto;

import com.bgwb0007.intro.springboot.domain.contact.Contact;
import com.bgwb0007.intro.springboot.domain.profiles.Profiles;
import com.bgwb0007.intro.springboot.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
public class ContactGetOneResponseDto {
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
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public ContactGetOneResponseDto(Contact contact) {
        this.id = contact.getId();
        this.name = contact.getName();
        this.engName = contact.getEngName();
        this.logoFileName = contact.getLogoFileName();
        this.logoHtml = contact.getLogoHtml();
        this.siteId = contact.getSiteId();
        this.siteUrl = contact.getSiteUrl();
        this.sortOrder = contact.getSortOrder();
        this.profiles = contact.getProfiles();
        this.pageGubun = contact.getProfiles().getPageGubun();
        this.createdDate = contact.getCreatedDate();
        this.modifiedDate = contact.getModifiedDate();
    }
    public Map toMap(){
        Map<String, String > retMap = new HashMap<>();
        retMap.put("id", StringUtil.nvl(this.id));
        retMap.put("name",StringUtil.nvl(this.name));
        retMap.put("engName",StringUtil.nvl(this.engName));
        retMap.put("logoFileName",StringUtil.nvl(this.logoFileName));
        retMap.put("logoHtml",StringUtil.nvl(this.logoHtml));
        retMap.put("siteId",StringUtil.nvl(this.siteId));
        retMap.put("siteUrl",StringUtil.nvl(this.siteUrl));
        retMap.put("sortOrder",StringUtil.nvl(this.sortOrder));
        retMap.put("profiles",StringUtil.nvl(this.profiles.getId()));
        retMap.put("pageGubun",StringUtil.nvl(this.pageGubun));
        retMap.put("createdDate",StringUtil.nvl(this.createdDate));
        retMap.put("modifiedDate",StringUtil.nvl(this.modifiedDate));
        return retMap;
    }
}
