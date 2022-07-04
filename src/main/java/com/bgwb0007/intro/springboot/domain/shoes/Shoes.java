package com.bgwb0007.intro.springboot.domain.shoes;


import com.bgwb0007.intro.springboot.web.dto.ShoesSaveRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Shoes {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemName;
    private String  storeFileName1;
    private String  storeFileName2;
}
