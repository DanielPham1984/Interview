package com.centralbank.main;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.nio.charset.Charset;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.centralbank.main.controller.HomeController;
import com.centralbank.main.entity.ClimateObj;
@RunWith(SpringJUnit4ClassRunner.class)
public class InterviewApplicationTests {
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
    @InjectMocks
    private HomeController homeController;
    
    ClimateObj returnObj = new ClimateObj();
    
    private MockMvc mockMvc;
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
    }
    @Test
    public void testPostToDetail() throws Exception {
    	  String jsonRequest = "{\"stationName\":\"CHEMAINUS\"}";
    	  mockMvc.perform(
                  post("/detail")
                          .contentType(APPLICATION_JSON_UTF8)
                          .content(jsonRequest.getBytes()))
                  .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testUrlNotFound()  throws Exception {
    	 mockMvc.perform(get("/"))
    	 .andExpect(model().attributeExists("climateObjs"))
    	 .andExpect(model().attributeExists("datepicker"))
    	 .andExpect(status().isOk());
  
    }
}