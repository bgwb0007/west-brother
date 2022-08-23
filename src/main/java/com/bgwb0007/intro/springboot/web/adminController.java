package com.bgwb0007.intro.springboot.web;

import com.bgwb0007.intro.springboot.service.ContactService;
import com.bgwb0007.intro.springboot.service.ProfilesService;
import com.bgwb0007.intro.springboot.service.ResumeService;
import com.bgwb0007.intro.springboot.service.TokensService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class adminController {
    @Autowired
    private ProfilesService profilesService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private ResumeService resumeService;
    @Autowired
    private TokensService tokensService;

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
    @GetMapping("/admin/resume")
    public String admResumeList(Model model) throws JsonProcessingException {
        model.addAttribute("resume",resumeService.findAll());
        return "admin/resume/admResume";
    }
    @GetMapping("/admin/tokens")
    public String admTokensList(Model model) throws JsonProcessingException {
        model.addAttribute("tokenList", tokensService.findAll());
        model.addAttribute("tokensListSize", tokensService.findAll().size());
        return "admin/tokens/admTokens";
    }



    @GetMapping("/admin/career-save")
    public String admCareerSave(){
        return "admin/resume/admCareer-save";
    }
    @GetMapping("/admin/career-update/{id}")
    public String admCareerUpdate(){
        return "admin/resume/admCareer-update";
    }
    @GetMapping("/admin/education-save")
    public String admEducationSave(){
        return "admin/resume/admEducation-save";
    }
    @GetMapping("/admin/education-update/{id}")
    public String admEducationUpdate(){
        return "admin/resume/admEducation-update";
    }
    @GetMapping("/admin/certificate-save")
    public String admCertificateSave(){
        return "admin/resume/admCertificate-save";
    }
    @GetMapping("/admin/certificate-update/{id}")
    public String admCertificateUpdate(){
        return "admin/resume/admCertificate-update";
    }
    @GetMapping("/admin/project-save")
    public String admProjectSave(){
        return "admin/resume/admProject-save";
    }
    @GetMapping("/admin/project-update/{id}")
    public String admProjectUpdate(){
        return "admin/resume/admProject-update";
    }
    @GetMapping("/admin/tokens-save")
    public String admTokensSave(){
        return "admin/tokens/admTokens-save";
    }

    @GetMapping("/admin/insta-token") //인스타 api 토큰 발급용 페이지
    public String admInstagramToken(){
        return "admin/instagram/admInstagramToken";
    }

}
