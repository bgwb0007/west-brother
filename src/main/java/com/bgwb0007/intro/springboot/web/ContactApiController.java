package com.bgwb0007.intro.springboot.web;

import com.bgwb0007.intro.springboot.service.ContactService;
import com.bgwb0007.intro.springboot.web.dto.ContactListResponseDto;
import com.bgwb0007.intro.springboot.web.dto.ContactSaveRequestDto;
import com.bgwb0007.intro.springboot.web.dto.ContactUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@RestController
public class ContactApiController {
    private final ContactService contactService;

    @PostMapping("/api/v1/contact")
    public Long save(@RequestBody ContactSaveRequestDto requestDto){
        return contactService.save(requestDto);
    }
    @PutMapping("/api/v1/contact/{id}")
    public Long update(@PathVariable Long id, @RequestBody ContactUpdateRequestDto requestDto){
        return contactService.update(id,requestDto);
    }
    @GetMapping("/api/v1/contact")
    public List<Map> findAllOrderBySortOrderAscForHtml (){
        return contactService.findAllOrderBySortOrderAscForHtml();
    }

    @DeleteMapping("/api/v1/contact/{id}")
    public Long delete(@PathVariable Long id){ return contactService.delete(id);}
}
