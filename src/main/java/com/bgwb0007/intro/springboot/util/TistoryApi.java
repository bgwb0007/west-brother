package com.bgwb0007.intro.springboot.util;

import com.bgwb0007.intro.springboot.service.TokensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class TistoryApi {
    @Autowired
    private TokensService tokensService;

    public String getAll(){
        String accessToken = tokensService.findByName("티스토리").getAccessToken();
        String url = "https://www.tistory.com/apis/post/list?output=json&blogName=west-brother&page=1"
                + "&access_token=" + accessToken;
        return sendHttp(url);
    }
    public String getDetail(String id){
        String accessToken = tokensService.findByName("티스토리").getAccessToken();
        String url = "https://www.tistory.com/apis/post/read?blogName=west-brother&output=json"
               + "&access_token=" + accessToken
               + "&postId=" + id;
        return sendHttp(url);
    }
    private String sendHttp(String url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

        HttpEntity<?> requestMessage = new HttpEntity<>(body, httpHeaders);
        HttpEntity<String> response = restTemplate.getForEntity(url, String.class);

        return response.getBody();
    }
}
