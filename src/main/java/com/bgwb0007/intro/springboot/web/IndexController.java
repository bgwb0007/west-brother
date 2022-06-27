package com.bgwb0007.intro.springboot.web;

import com.bgwb0007.intro.springboot.service.ContactService;
import com.bgwb0007.intro.springboot.service.ProfilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {
    @Autowired
    private ProfilesService profilesService;
    @Autowired
    private ContactService contactService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("profiles", profilesService.findByPageGubun("메인"));
        model.addAttribute("contacts", contactService.findByPageGubunOrderBySortOrderAscForHtml("메인"));
        return "index";
    }
    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts/posts-save";
    }
    @GetMapping("/resume")
    public String profiles(){
        return "resume/resume";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin/admin";
    }
    @GetMapping("/admin/profiles")
    public String admProfilesList(Model model){
        model.addAttribute("profilesList", profilesService.findAll());
        model.addAttribute("profilesListSize", profilesService.findAll().size());
        return "admin/profiles/admProfiles";
    }
    @GetMapping("/admin/profiles-update/{id}")
    public String admProfilesUpdate(@PathVariable Long id, Model model){
        model.addAttribute("profilesFindById", profilesService.findById(id));
        return "admin/profiles/admProfiles-update";
    }
    @GetMapping("/admin/contact")
    public String admContactList(Model model){
        model.addAttribute("contactList",contactService.findAllOrderBySortOrderAscForAdm());
        model.addAttribute("contactListSize", contactService.findAllOrderBySortOrderAscForAdm().size());
        return "admin/contact/admContact";
    }
    @GetMapping("/admin/contact-save")
    public String admContactSave(){
        return "admin/contact/admContact-save";
    }
    @GetMapping("/admin/contact-update/{id}")
    public String admContactUpdate(@PathVariable Long id, Model model){
        model.addAttribute("contacts", contactService.findById(id));
        return "admin/contact/admContact-update";
    }

}
