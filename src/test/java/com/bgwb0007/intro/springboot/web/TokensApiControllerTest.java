package com.bgwb0007.intro.springboot.web;

import com.bgwb0007.intro.springboot.domain.tokens.Tokens;
import com.bgwb0007.intro.springboot.domain.tokens.TokensRepository;
import com.bgwb0007.intro.springboot.web.dto.TokensSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TokensApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TokensRepository tokensRepository;

    @After
    public void tearDown() throws Exception{
        tokensRepository.deleteAll();
    }
    @Test
    public void Tokens_등록하기() throws Exception{
        String name = "인스타";
        String url_input = "인스타그램";
        String accessToken = "sdjfl1aksjkl23jl13jlkq";
        String expirationPeriod = "1개월";
        TokensSaveRequestDto requestDto = TokensSaveRequestDto.builder()
                .name(name)
                .url(url_input)
                .accessToken(accessToken)
                .expirationPeriod(expirationPeriod)
                .build();
        String url = "http://localhost:" + port + "/api/v1/tokens";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto,Long.class);

//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Tokens> all = tokensRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(name);
        assertThat(all.get(0).getAccessToken()).isEqualTo(accessToken);
    }
//    @Test
//    public void Posts_수정하기() throws Exception {
//        //given
//        Posts savedPosts = postsRepository.save(Posts.builder()
//                .title("테스트게시글")
//                .content("내용입니다")
//                .author("나")
//                .build());
//
//        Long updateId = savedPosts.getId();
//        String expectedTitle = "제목";
//        String expectedContent = "냉무";
//
//        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
//                .title(expectedTitle)
//                .content(expectedContent)
//                .build();
//        String url = "http://localhost:"+port+"/api/v1/posts/"+updateId;
//        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);
//
//        //when
//        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);
//
//        //then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);
//        List<Posts> all = postsRepository.findAll();
//        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
//        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
//
//    }
}
