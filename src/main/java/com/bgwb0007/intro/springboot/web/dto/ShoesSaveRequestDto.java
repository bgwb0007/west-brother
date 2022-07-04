package com.bgwb0007.intro.springboot.web.dto;

import com.bgwb0007.intro.springboot.domain.shoes.Shoes;
import com.bgwb0007.intro.springboot.util.FileStore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShoesSaveRequestDto {
    private Long shoesId;
    private String itemName;
    private List<MultipartFile> imageFiles;


    public Shoes toEntityAndSaveFiles() throws IOException {
        FileStore fileStore = new FileStore();
        List<String> storeImageFiles = fileStore.storeFiles(imageFiles);

        HashMap<String, String> storeFileMap = new HashMap<>();
        storeFileMap.put("storeFileName1","");
        storeFileMap.put("storeFileName2","");
        String key = "";
        for(int i=1; i<storeImageFiles.size() + 1 ; i++){
            key = "storeFileName" + i;
            storeFileMap.put(key,storeImageFiles.get(i-1));
        }
        Shoes shoes = new Shoes();
        shoes.setItemName(itemName);
        shoes.setStoreFileName1(storeFileMap.get("storeFileName1"));
        shoes.setStoreFileName2(storeFileMap.get("storeFileName2"));
        return shoes;
    }
}
