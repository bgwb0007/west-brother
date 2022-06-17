package com.bgwb0007.intro.springboot.domain.contact;

import com.bgwb0007.intro.springboot.domain.profiles.Profiles;
import com.bgwb0007.intro.springboot.domain.profiles.ProfilesRepository;
import com.bgwb0007.intro.springboot.web.dto.ContactUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactRepositoryTest {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ProfilesRepository profilesRepository;

    @After
    public void cleanup(){
        contactRepository.deleteAll();
        profilesRepository.deleteAll();
    }

    @Test
    public void CONTACT_저장_후_전체조회(){
        //given
        String name = "인스타그램";
        String engName = "instagram";
        String logoFileName = "tjgud.png";
        String logoHtml = "/images/app/tjgud.png";
        String siteId = "west_brotherr";
        String siteUrl = "www.insta.com";
        Integer sortOrder = 20;
        Profiles profiles = Profiles.builder()
                .name("임서형")
                .content1("웹개발자1")
                .content2("웹개발자222")
                .photoFileName("aaaaa.png")
                .photoPath("/wesfsd/dfasf.dsf")
                .pageGubun("메인")
                .build();
        profilesRepository.save(profiles);

        contactRepository.save(Contact.builder()
                .name(name)
                .engName(engName)
                .logoFileName(logoFileName)
                .logoHtml(logoHtml)
                .siteId(siteId)
                .siteUrl(siteUrl)
                .sortOrder(sortOrder)
                .profiles(profiles)
                .build());
        //when
        List<Contact> contactList = contactRepository.findAllOrderBySortOrderAsc();

        //then
        Contact contact = contactList.get(0);
        assertThat(contact.getName()).isEqualTo(name);
        assertThat(contact.getSiteId()).isEqualTo(siteId);
    }
}
