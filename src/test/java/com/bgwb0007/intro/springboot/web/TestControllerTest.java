package com.bgwb0007.intro.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = IndexController.class)
public class TestControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void test가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/test"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }
}
