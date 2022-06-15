package com.bgwb0007.intro.springboot.web;

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

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("profiles", profilesService.findAll());
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
        model.addAttribute("profiles", profilesService.findAll());
        model.addAttribute("profilesSize", profilesService.findAll().size());
        return "admin/profiles/admProfiles";
    }
    @GetMapping("/admin/profiles/{id}")
    public String admProfilesUpdate(@PathVariable Long id, Model model){
        model.addAttribute("profilesFindById", profilesService.findById(id));
        return "admin/profiles/admProfiles-update";
    }

}
