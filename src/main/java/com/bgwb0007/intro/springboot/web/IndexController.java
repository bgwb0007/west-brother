package com.bgwb0007.intro.springboot.web;

import com.bgwb0007.intro.springboot.service.*;
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
    @Autowired
    private ResumeService resumeService;


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
    public String resume(Model model){
        model.addAttribute("resume", resumeService.findAll());
              return "resume/resume";
    }
    @GetMapping("/daily")
    public String daily(){
        return "daily/daily";
    }



}
