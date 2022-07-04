package com.bgwb0007.intro.springboot.web;

import com.bgwb0007.intro.springboot.domain.shoes.Shoes;
import com.bgwb0007.intro.springboot.service.ShoesService;
import com.bgwb0007.intro.springboot.util.FileStore;
import com.bgwb0007.intro.springboot.web.dto.ShoesSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Controller
public class ShoesApiController {
    private final ShoesService shoesService;

    @GetMapping("/shoes")
    public String shoes(){
        return "shoes/shoes";
    }

    @PostMapping("/shoes/new")
    public String saveItem(@ModelAttribute ShoesSaveRequestDto requestDto, RedirectAttributes redirectAttributes) throws IOException {
        redirectAttributes.addAttribute("shoesId", shoesService.save(requestDto));
        return "redirect:/shoes/{shoesId}";
    }

}
