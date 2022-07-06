package com.bgwb0007.intro.springboot.web.dto;

import com.bgwb0007.intro.springboot.domain.shoes.Shoes;
import com.bgwb0007.intro.springboot.util.FileStore;
import com.bgwb0007.intro.springboot.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShoesSaveRequestDto {
    private String name;
    private String productCode;
    private String content;
    private String status;
    private String purchaseDate; // yyyy-mm-dd
    private String buy;
    private String releasePrice;
    private String sellPrice;
    private MultipartFile mainImage;
    private List<MultipartFile> imageFiles;


    public Shoes saveFilesAndToEntity() throws IOException {
        FileStore fileStore = new FileStore();
        String storeMainImage = fileStore.storeFile(mainImage);

        List<String> storeImageFiles = fileStore.storeFiles(imageFiles);
        HashMap<String, String> storeFileMap = new HashMap<>();
        storeFileMap.put("image1","");
        storeFileMap.put("image2","");
        storeFileMap.put("image3","");
        storeFileMap.put("image4","");
        storeFileMap.put("image5","");
        String key = "";
        for(int i=1; i<storeImageFiles.size() + 1 ; i++){
            key = "image" + i;
            storeFileMap.put(key,storeImageFiles.get(i-1));
        }
        LocalDate pDate = purchaseDate.equals("") ? null : LocalDate.parse(purchaseDate);

        Shoes shoes = Shoes.builder()
                .name(name)
                .productCode(productCode)
                .content(content)
                .status(status)
                .purchaseDate(pDate)
                .buy(buy)
                .releasePrice(releasePrice)
                .sellPrice(sellPrice)
                .mainImage(StringUtil.nvl(storeMainImage))
                .image1(storeFileMap.get("image1"))
                .image2(storeFileMap.get("image2"))
                .image3(storeFileMap.get("image3"))
                .image4(storeFileMap.get("image4"))
                .image5(storeFileMap.get("image5"))
                .build();
        return shoes;
    }
}
