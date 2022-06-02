package com.bgwb0007.intro.springboot.web;

import com.bgwb0007.intro.springboot.service.ContactService;
import com.bgwb0007.intro.springboot.web.dto.ContactListResponseDto;
import com.bgwb0007.intro.springboot.web.dto.ContactSaveRequestDto;
import com.bgwb0007.intro.springboot.web.dto.ContactUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class ContactApiController {
    private final ContactService contactService;

    @PostMapping("/api/v1/profiles/contact")
    public Long save(@RequestBody ContactSaveRequestDto requestDto){
        return contactService.save(requestDto);
    }
    @PutMapping("/api/v1/profiles/contact/{id}")
    public Long update(@PathVariable Long id, @RequestBody ContactUpdateRequestDto requestDto){
        return contactService.update(id,requestDto);
    }
    @GetMapping("/api/v1/profiles/contact")
    public List<ContactListResponseDto> findAllOrderBySortOrderAsc (){
        return contactService.findAllOrderBySortOrderAsc();
    }
}
