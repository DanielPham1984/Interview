package com.centralbank.main;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.centralbank.main.Util.Utils;
import com.centralbank.main.controller.HomeController;
import com.centralbank.main.entity.ClimateObj;
@RunWith(SpringJUnit4ClassRunner.class)
public class InterviewApplicationTests {
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
    @InjectMocks
    private HomeController homeController;
    

    
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
                  .andExpect(status().isOk())
                  .andExpect(content().json("{stationName: \"CHEMAINUS\", province: \"BC\", date: \"04/01/2018\", meanTemp: \"15.1\", highestTemp: \"26.5\"}"));
    }
    @Test
    public void testUrlNotFound()  throws Exception {
    	 mockMvc.perform(get("/"))
    	 .andExpect(model().attributeExists("climateObjs"))
    	 .andExpect(model().attributeExists("datepicker"))
    	 .andExpect(status().isOk());
  
    }
    
    @Test 
    public void testUtilsGetRecord () throws Exception {
    	String name = "CHEMAINUS";
    	ClimateObj expectedObj = new ClimateObj();
	    	expectedObj.setStationName("CHEMAINUS");
	    	expectedObj.setProvince("BC");
	    	expectedObj.setMeanTemp("15.1");
	    	expectedObj.setDate("04/01/2018");
	    	expectedObj.setHighestTemp("26.5");
	    	expectedObj.setLowestTemp("7");
	  	ClimateObj result = Utils.getDetailBaseOnMeanTemp(name);
	  	assertThat(result).isEqualToComparingFieldByField(expectedObj);
	  	
    	
    }
}