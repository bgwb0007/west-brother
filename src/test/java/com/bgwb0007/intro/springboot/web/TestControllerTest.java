package com.bgwb0007.intro.springboot.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = TestController.class)
@MockBean(JpaMetamodelMappingContext.class)
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
    @Test
    public void tt() throws Exception{
        //given
        ObjectMapper mapper = new ObjectMapper();
        Map map = new HashMap<>();
        map.put("123",444);
        map.put("4444","5555");
        List list = new ArrayList<>();
        list.add(map);
        System.out.println(mapper.writeValueAsString(list));
        System.out.println(mapper.writeValueAsString(list).getClass());
        //when

        //then
    }
}
