package com.bgwb0007.intro.springboot.web;

import com.bgwb0007.intro.springboot.domain.resume.CareerRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CareerApiControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private CareerRepository careerRepository;
    @After
    public void tearDown() throws Exception{
        careerRepository.deleteAll();
    }

    @Test
    public void Career_등록하기() throws Exception {
        //given

        //when

        //then
    }
}
