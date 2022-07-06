package com.bgwb0007.intro.springboot.domain.shoes;


import com.bgwb0007.intro.springboot.web.dto.ShoesSaveRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Data
public class Shoes {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String productCode;
    private String content;
    private String status;
    private LocalDate purchaseDate;
    private String buy;
    private String releasePrice;
    private String sellPrice;
    private String mainImage;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String image5;


    @Builder
    public Shoes(String name, String productCode, String content, String status, LocalDate purchaseDate, String buy, String releasePrice, String sellPrice, String mainImage, String image1, String image2, String image3, String image4, String image5) {
        this.name = name;
        this.productCode = productCode;
        this.content = content;
        this.status = status;
        this.purchaseDate = purchaseDate;
        this.buy = buy;
        this.releasePrice = releasePrice;
        this.sellPrice = sellPrice;
        this.mainImage = mainImage;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
        this.image5 = image5;
    }
}
