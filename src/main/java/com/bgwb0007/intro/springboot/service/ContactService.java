package com.bgwb0007.intro.springboot.service;

import com.bgwb0007.intro.springboot.domain.contact.Contact;
import com.bgwb0007.intro.springboot.domain.contact.ContactRepository;
import com.bgwb0007.intro.springboot.domain.profiles.Profiles;
import com.bgwb0007.intro.springboot.domain.profiles.ProfilesRepository;
import com.bgwb0007.intro.springboot.web.dto.ContactGetOneResponseDto;
import com.bgwb0007.intro.springboot.web.dto.ContactListResponseDto;
import com.bgwb0007.intro.springboot.web.dto.ContactSaveRequestDto;
import com.bgwb0007.intro.springboot.web.dto.ContactUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ContactService {
    private final ContactRepository contactRepository;
    private final ProfilesRepository profilesRepository;
    @Transactional
    public Long save(ContactSaveRequestDto requestDto){
        Profiles profiles = profilesRepository.findByPageGubun(requestDto.getPageGubun())
                .orElseThrow(()->new IllegalArgumentException("해당 노출위치에 저장된 프로필이 없습니다. pageGubun="+requestDto.getPageGubun()));
        return contactRepository.save(new Contact(requestDto,profiles)).getId();
    }
    @Transactional
    public Long update(Long id, ContactUpdateRequestDto requestDto){
        Contact contact = contactRepository.findById(id)
                .orElseThrow(()-> new
                        IllegalArgumentException("해당 연락처 기록이 없습니다. id= " + id));
        contact.update(requestDto);
        return id;
    }
    @Transactional
    public List<ContactListResponseDto> findAllOrderBySortOrderAscForAdm(){
        return  contactRepository.findAllOrderBySortOrderAsc().stream()
                .map(ContactListResponseDto::new)
                .collect(Collectors.toList());
    }
    @Transactional
    public List<Map> findByPageGubunOrderBySortOrderAscForHtml(String pageGubun){
        List<Contact> findAll = contactRepository.findAllOrderBySortOrderAsc().stream()
                .collect(Collectors.toList());
        List<Map> retList = new ArrayList<>();
        for(Contact contact : findAll){
           if(!contact.getProfiles().getPageGubun().equals(pageGubun)) continue;
            retList.add(contact.toMap());
        }
        return retList;
    }
    @Transactional
    public ContactGetOneResponseDto findById(Long id){
        Contact contact = contactRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 연락처 기록이 없습니다. id="+id));
        return new ContactGetOneResponseDto(contact);
    }
    @Transactional
    public Long delete(Long id){
        Contact contact = contactRepository.findById(id)
                .orElseThrow(()->new
                        IllegalArgumentException("해당 연락처 기록이 없습니다. id=" + id));
        contactRepository.delete(contact);
        return id;
    }
}
