package com.bgwb0007.intro.springboot.web;

import com.bgwb0007.intro.springboot.service.ShoesService;
import com.bgwb0007.intro.springboot.util.FileStore;
import com.bgwb0007.intro.springboot.web.dto.ShoesSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.MalformedURLException;


@Slf4j
@RequiredArgsConstructor
@Controller
public class ShoesApiController {
    private final ShoesService shoesService;
    private final FileStore fileStore;

    @GetMapping("/shoes")
    public String shoes(Model model){
        model.addAttribute("shoesList",shoesService.findAll());
        return "shoes/shoes";
    }
    @GetMapping("/shoes/new")
    public String saveShoesForm(){
        return "shoes/shoes-save";
    }

    @PostMapping("/shoes/new")
    public String saveItem(@ModelAttribute ShoesSaveRequestDto requestDto, RedirectAttributes redirectAttributes) throws IOException {
//        redirectAttributes.addAttribute("fileName", shoesService.save(requestDto)); 쿼리스트링
        return "redirect:/shoes";
    }
    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws
            MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }

}
