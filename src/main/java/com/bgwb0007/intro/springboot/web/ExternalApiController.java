package com.bgwb0007.intro.springboot.web;

import com.bgwb0007.intro.springboot.util.ExternalApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ExternalApiController {
    private final ExternalApi externalApi;

    @GetMapping("/api/v1/tistoryList/{page}")
    public String getTistoryAllList(@PathVariable String page){
        return externalApi.getTistoryAll(page);
    }
    @GetMapping("/api/v1/tistory/category")
    public String getTistoryCategory(){
        return externalApi.getTistoryCategory();
    }
    @GetMapping("/api/v1/tistory/{id}")
    public String getTistoryDetail(@PathVariable String id){
        return externalApi.getTistoryDetail(id);
    }
    @GetMapping("/api/v1/instagram")
    public String getInstagramAllList(){
        return externalApi.getInstagramAll();
    }
}
