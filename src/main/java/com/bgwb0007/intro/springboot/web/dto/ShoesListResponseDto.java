package com.bgwb0007.intro.springboot.web.dto;

import com.bgwb0007.intro.springboot.domain.shoes.Shoes;
import com.bgwb0007.intro.springboot.util.StringUtil;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class ShoesListResponseDto {
    private Long id;
    private String name;
    private String productCode;
    private String brand;
    private String size;
    private String content;
    private String status;
    private String purchaseDate; // yyyy-mm-dd
    private String buy;
    private String releasePrice;
    private String sellPrice;
    private String mainImage;
    private List<String> imageList;

    public ShoesListResponseDto(Shoes shoes) {
        this.id = shoes.getId();
        this.name = StringUtil.nvl(shoes.getName());
        this.productCode = StringUtil.nvl(shoes.getProductCode());
        this.brand = StringUtil.nvl(shoes.getBrand());
        this.size = StringUtil.nvl(shoes.getSize());
        this.content = StringUtil.nvl(shoes.getContent());
        this.status = StringUtil.nvl(shoes.getStatus());
        this.purchaseDate = StringUtil.nvl(shoes.getPurchaseDate());
        this.buy = StringUtil.nvl(shoes.getBuy());
        this.releasePrice = StringUtil.nvl(shoes.getReleasePrice());
        this.sellPrice = StringUtil.nvl(shoes.getSellPrice());
        this.mainImage = StringUtil.nvl(shoes.getMainImage());
        this.imageList = new ArrayList<>();
        if(!shoes.getImage1().equals("")) this.imageList.add(shoes.getImage1());
        if(!shoes.getImage2().equals("")) this.imageList.add(shoes.getImage2());
        if(!shoes.getImage3().equals("")) this.imageList.add(shoes.getImage3());
        if(!shoes.getImage4().equals("")) this.imageList.add(shoes.getImage4());
        if(!shoes.getImage5().equals("")) this.imageList.add(shoes.getImage5());
    }
}
