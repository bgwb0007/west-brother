package com.bgwb0007.intro.springboot.web;

import com.bgwb0007.intro.springboot.service.ProfilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @Autowired
    private ProfilesService profilesService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("profiles", profilesService.findAll().toArray());
        return "index";
    }
    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts/posts-save";
    }
    @GetMapping("/profiles")
    public String profiles(){
        return "profiles/profiles";
    }



    @GetMapping("/admin")
    public String admin(){
        return "admin/admin";
    }
}
