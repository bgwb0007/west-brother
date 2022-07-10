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
import java.util.List;
import java.util.Map;



@RequiredArgsConstructor
@RestController
public class ShoesApiController {
    private final ShoesService shoesService;

    @GetMapping("/api/v1/shoes")
    public List<Map> findAllToMap(){
        return shoesService.findAllToMap();
    }
    @GetMapping("/api/v1/shoes/{status}")
    public List<Map> findAllByStatusToMap(@PathVariable String status){
        return shoesService.findAllByStatusToMap(status);
    }

}
