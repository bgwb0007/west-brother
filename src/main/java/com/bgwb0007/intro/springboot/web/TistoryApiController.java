package com.bgwb0007.intro.springboot.web;

import com.bgwb0007.intro.springboot.util.TistoryApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class TistoryApiController {
    private final TistoryApi tistoryApi;

    @GetMapping("/api/v1/tistory")
    public String getAllList(){
        return tistoryApi.getAll();
    }
    @GetMapping("/api/v1/tistory/{id}")
    public String getDetail(@PathVariable String id){
        return tistoryApi.getDetail(id);
    }
}
