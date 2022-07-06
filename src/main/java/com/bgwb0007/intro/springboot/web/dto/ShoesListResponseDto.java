package com.bgwb0007.intro.springboot.web.dto;

import com.bgwb0007.intro.springboot.domain.shoes.Shoes;
import com.bgwb0007.intro.springboot.util.StringUtil;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ShoesListResponseDto {
    private Long id;
    private String name;
    private String productCode;
    private String content;
    private String status;
    private String buy;
    private String releasePrice;
    private String sellPrice;
    private String mainImage;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String image5;

    public ShoesListResponseDto(Shoes shoes) {
        this.id = shoes.getId();
        this.name = StringUtil.nvl(shoes.getName());
        this.productCode = StringUtil.nvl(shoes.getProductCode());
        this.content = StringUtil.nvl(shoes.getContent());
        this.status = StringUtil.nvl(shoes.getStatus());
        this.buy = StringUtil.nvl(shoes.getBuy());
        this.releasePrice = StringUtil.nvl(shoes.getReleasePrice());
        this.sellPrice = StringUtil.nvl(shoes.getSellPrice());
        this.mainImage = StringUtil.nvl(shoes.getMainImage());
        this.image1 = StringUtil.nvl(shoes.getImage1());
        this.image2 = StringUtil.nvl(shoes.getImage2());
        this.image3 = StringUtil.nvl(shoes.getImage3());
        this.image4 = StringUtil.nvl(shoes.getImage4());
        this.image5 = StringUtil.nvl(shoes.getImage5());
    }
}
