package com.bgwb0007.intro.springboot.web;

import com.bgwb0007.intro.springboot.service.ShoesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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
