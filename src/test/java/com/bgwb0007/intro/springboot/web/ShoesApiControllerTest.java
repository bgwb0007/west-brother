package com.bgwb0007.intro.springboot.web;

import com.bgwb0007.intro.springboot.domain.shoes.Shoes;
import com.bgwb0007.intro.springboot.domain.shoes.ShoesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShoesApiControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ShoesRepository shoesRepository;
    @After
    public void tearDown() throws Exception{
        shoesRepository.deleteAll();
    }
    @Test
    public void Shoes_전체조회_테스트() throws Exception{
        //given
        String name = "테스트이름";
        String productCode = "테스트코드";
        String brand = "나이키";
        String size = "270";
        String content = "샀어요";
        String status = "사용중";
        LocalDate purchaseDate = LocalDate.now();
        String buy = "draw";
        String releasePrice = "130000";
        String sellPrice = "190000";
        String mainImage = "sadfj123-123-124-124.jpg";
        String image1 = "ddd";
        String image2 = "222";
        String image3 = "aaa";
        String image4 = "";
        String image5 = "";

        Long id = shoesRepository.save(Shoes.builder()
                .name(name)
                .productCode(productCode)
                .brand(brand)
                .size(size)
                .content(content)
                .status(status)
                .purchaseDate(purchaseDate)
                .buy(buy)
                .releasePrice(releasePrice)
                .sellPrice(sellPrice)
                .mainImage(mainImage)
                .image1(image1)
                .image2(image2)
                .image3(image3)
                .image4(image4)
                .image5(image5)
                .build()).getId();
        ObjectMapper objectMapper = new ObjectMapper();
        //when
        String url = "http://localhost:" + port + "/api/v1/shoes";
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(url,List.class);
        HashMap retMap = objectMapper.convertValue(responseEntity.getBody().get(0),HashMap.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(retMap.get("id")).isEqualTo(String.valueOf(id));
        assertThat(retMap.get("name")).isEqualTo(name);
        assertThat(retMap.get("productCode")).isEqualTo(productCode);
        assertThat(retMap.get("brand")).isEqualTo(brand);
        assertThat(retMap.get("size")).isEqualTo(size);
    }
    @Test
    public void Shoes_status_조회_테스트() throws Exception{
        //given
        String name = "테스트이름";
        String productCode = "테스트코드";
        String brand = "나이키";
        String size = "270";
        String content = "샀어요";
        String status = "사용중";
        LocalDate purchaseDate = LocalDate.now();
        String buy = "draw";
        String releasePrice = "130000";
        String sellPrice = "190000";
        String mainImage = "sadfj123-123-124-124.jpg";
        String image1 = "ddd";
        String image2 = "222";
        String image3 = "aaa";
        String image4 = "";
        String image5 = "";

        Long id = shoesRepository.save(Shoes.builder()
                .name(name)
                .productCode(productCode)
                .brand(brand)
                .size(size)
                .content(content)
                .status(status)
                .purchaseDate(purchaseDate)
                .buy(buy)
                .releasePrice(releasePrice)
                .sellPrice(sellPrice)
                .mainImage(mainImage)
                .image1(image1)
                .image2(image2)
                .image3(image3)
                .image4(image4)
                .image5(image5)
                .build()).getId();
        ObjectMapper objectMapper = new ObjectMapper();
        //when
        String url = "http://localhost:" + port + "/api/v1/shoes/" + status;
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(url,List.class);
        HashMap retMap = objectMapper.convertValue(responseEntity.getBody().get(0),HashMap.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(retMap.get("id")).isEqualTo(String.valueOf(id));
        assertThat(retMap.get("name")).isEqualTo(name);
        assertThat(retMap.get("productCode")).isEqualTo(productCode);
        assertThat(retMap.get("brand")).isEqualTo(brand);
        assertThat(retMap.get("size")).isEqualTo(size);
    }
}
