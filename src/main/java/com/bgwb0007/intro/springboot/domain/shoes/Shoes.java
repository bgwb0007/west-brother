package com.bgwb0007.intro.springboot.domain.shoes;

import com.bgwb0007.intro.springboot.util.StringUtil;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Entity
@NoArgsConstructor
@Data
public class Shoes {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String productCode;
    private String brand;
    @Column(length = 3)
    private String size;
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
    public Shoes(String name, String productCode, String brand, String size,String content, String status, LocalDate purchaseDate, String buy, String releasePrice, String sellPrice, String mainImage, String image1, String image2, String image3, String image4, String image5) {
        this.name = name;
        this.productCode = productCode;
        this.brand = brand;
        this.size = size;
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
    public static Map toMap(Shoes shoes){
        HashMap retMap = new HashMap<>();
        retMap.put("id",StringUtil.nvl(shoes.getId()));
        retMap.put("name",StringUtil.nvl(shoes.getName()));
        retMap.put("productCode",StringUtil.nvl(shoes.getProductCode()));
        retMap.put("brand",StringUtil.nvl(shoes.getBrand()));
        retMap.put("size",StringUtil.nvl(shoes.getSize()));
        retMap.put("content",StringUtil.nvl(shoes.getContent()));
        retMap.put("status",StringUtil.nvl(shoes.getStatus()));
        retMap.put("purchaseDate",StringUtil.nvl(shoes.getPurchaseDate()));
        String pDateS8 = StringUtil.nvl(shoes.getPurchaseDate().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        retMap.put("purchaseDateS8",pDateS8);
        retMap.put("buy",StringUtil.nvl(shoes.getBuy()));
        retMap.put("releasePrice",StringUtil.nvl(shoes.getReleasePrice()));
        retMap.put("sellPrice",StringUtil.nvl(shoes.getSellPrice()));
        retMap.put("mainImage",StringUtil.nvl(shoes.getMainImage()));
        ArrayList imageList = new ArrayList<>();
        if(!shoes.getImage1().equals("")) imageList.add(shoes.getImage1());
        if(!shoes.getImage2().equals("")) imageList.add(shoes.getImage2());
        if(!shoes.getImage3().equals("")) imageList.add(shoes.getImage3());
        if(!shoes.getImage4().equals("")) imageList.add(shoes.getImage4());
        if(!shoes.getImage5().equals("")) imageList.add(shoes.getImage5());
        retMap.put("imageList", imageList);
        return retMap;
    }

}
