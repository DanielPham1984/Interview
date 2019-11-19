package com.centralbank.main.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.centralbank.main.entity.ClimateObj;
import com.centralbank.main.entity.DatePicker;
import com.centralbank.main.entity.DetailRequest;

/*****************************
 * 
 * @author An Pham
 *
 */

@Controller
public class HomeController {
	static final Logger logger = Logger.getLogger(HomeController.class);
    private final String HOME_VIEW = "home";
    private final String RESULT_VIEW = "result";
     

   	
    @GetMapping("/")
    public String main(Model model) {
    	try {
    		logger.info("Display Main Screen ");
			List<ClimateObj> objects = loadCSVData();
			model.addAttribute("climateObjs", objects);
			model.addAttribute("datepicker", new DatePicker());
		} catch (IOException e) {
			logger.warn("Could not load the obj: "+ e.getMessage());
		}
        return HOME_VIEW;
    }

    @PostMapping("/")
    public String datepickerSubmit(@ModelAttribute("datepicker") final  DatePicker datepicker,
    		 Model model) {
    	try {
    		List<ClimateObj> objects = loadCSVDataWithinRange( datepicker.startDate.getTime(),datepicker.endDate.getTime());
    		model.addAttribute("climateObjs", objects);
			
		} catch (Exception e) {
			logger.warn("Could not load the data with time picker: "+ e.getMessage());
			
		}
    	
        return RESULT_VIEW;
    }
    
    
    @PostMapping("/detail")
    public ResponseEntity<?> getSearchResultViaAjax(
            @Valid @RequestBody DetailRequest request, Errors errors) {

    	ClimateObj result = new ClimateObj();
    

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {
        	result.setStationName("Could not find the stationName");
            return ResponseEntity.badRequest().body(result);

        }

		try {
			result = getDetailBaseOnMeanTemp(request.getStationName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.warn("Could not load the data in detail "+ e.getMessage());
		}
   

        return ResponseEntity.ok(result);

    }

    private ClimateObj getDetailBaseOnMeanTemp(String name) throws IOException {
    	ClimateObj body = new ClimateObj();
    	
      	Resource resource = new ClassPathResource("csv/eng-climate-summary.csv");
        InputStream input = resource.getInputStream();	
    	String line = "";
        String cvsSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(input))) {
        	boolean findRecord = false;
            while ((line = br.readLine()) != null) {
            	ClimateObj climate = new ClimateObj();
                String[] objects = line.split(cvsSplitBy);
                
                if(objects.length >= 1) {
                	climate.setStationName(objects[0]); 
                	if (climate.getStationName().equals(name))
                		findRecord = true;
                }
                if(objects.length >= 2) {
                	climate.setProvince(objects[1]); 
                }
                if(objects.length >= 3) {
                	climate.setDate(objects[2]); 
                }
                if(objects.length >= 4) {
                	climate.setMeanTemp(objects[3]); 
                	
                }
                
                if(objects.length >= 5) {
                	climate.setHighestTemp(objects[4]); 
                }
                if(objects.length >= 6) {
                	climate.setLowestTemp(objects[5]); 
                }
                if (findRecord) {
                	return climate;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    
    	return body;
    }
    private List<ClimateObj> loadCSVDataWithinRange(long startDate, long endDate) throws IOException, ParseException {
    	Resource resource = new ClassPathResource("csv/eng-climate-summary.csv");
        InputStream input = resource.getInputStream();	
    	String line = "";
        String cvsSplitBy = ",";
    	List<ClimateObj> list = new ArrayList<ClimateObj>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(input))) {

            while ((line = br.readLine()) != null) {
            	boolean isWithinRange = false;
            	ClimateObj climate = new ClimateObj();
                String[] objects = line.split(cvsSplitBy);
                
                if(objects.length >= 1) {
                	climate.setStationName(objects[0]); 
                }
                if(objects.length >= 2) {
                	climate.setProvince(objects[1]); 
                }
                if(objects.length >= 3) {
                	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                	Date date = sdf.parse(objects[2]);
                	long climateDate = date.getTime();
                	if((climateDate>startDate) && (climateDate<endDate))
                		isWithinRange = true;
                	climate.setDate(objects[2]); 
                }
                if(objects.length >= 4) {
                	climate.setMeanTemp(objects[3]); 
                }
                if(objects.length >= 5) {
                	climate.setHighestTemp(objects[4]); 
                }
                if(objects.length >= 6) {
                	climate.setLowestTemp(objects[5]); 
                }
                if (isWithinRange) {
                	list.add(climate);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    
    	return list;
    	
    }
    
    private List<ClimateObj> loadCSVData() throws IOException {
    	Resource resource = new ClassPathResource("csv/eng-climate-summary.csv");
        InputStream input = resource.getInputStream();	
    	String line = "";
        String cvsSplitBy = ",";
    	List<ClimateObj> list = new ArrayList<ClimateObj>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(input))) {

            while ((line = br.readLine()) != null) {
            	ClimateObj climate = new ClimateObj();
                String[] objects = line.split(cvsSplitBy);
                
                if(objects.length >= 1) {
                	climate.setStationName(objects[0]); 
                }
                if(objects.length >= 2) {
                	climate.setProvince(objects[1]); 
                }
                if(objects.length >= 3) {
                	climate.setDate(objects[2]); 
                }
                if(objects.length >= 4) {
                	climate.setMeanTemp(objects[3]); 
                }
                if(objects.length >= 5) {
                	climate.setHighestTemp(objects[4]); 
                }
                if(objects.length >= 6) {
                	climate.setLowestTemp(objects[5]); 
                }
                if (objects.length > 0 ) {
                	list.add(climate);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    
    	return list;
    	
    }

}
