package com.bgwb0007.intro.springboot.service;

import com.bgwb0007.intro.springboot.domain.contact.Contact;
import com.bgwb0007.intro.springboot.domain.contact.ContactRepository;
import com.bgwb0007.intro.springboot.web.dto.ContactListResponseDto;
import com.bgwb0007.intro.springboot.web.dto.ContactSaveRequestDto;
import com.bgwb0007.intro.springboot.web.dto.ContactUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ContactService {

    private final ContactRepository contactRepository;
    @Transactional
    public Long save(ContactSaveRequestDto requestDto){
        return contactRepository.save(requestDto.toEntity()).getId();
    }
    @Transactional
    public Long update(Long id, ContactUpdateRequestDto requestDto){
        Contact contact = contactRepository.findById(id)
                .orElseThrow(()-> new
                        IllegalArgumentException("해당 연락처 기록이 없습니다. id= " + id));
        contact.update(requestDto, contact.getProfiles());
        return id;
    }
    @Transactional
    public List<ContactListResponseDto> findAllOrderBySortOrderAsc(){
        return contactRepository.findAllOrderBySortOrderAsc().stream()
                .map(ContactListResponseDto::new)
                .collect(Collectors.toList());
    }
}
