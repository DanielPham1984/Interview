package com.centralbank.main.controller;

import java.io.IOException;
import java.util.List;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.centralbank.main.Util.Utils;
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
			List<ClimateObj> objects = Utils.loadCSVData();
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
    		List<ClimateObj> objects = Utils.loadCSVDataWithinRange( datepicker.startDate.getTime(),datepicker.endDate.getTime());
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
			result = Utils.getDetailBaseOnMeanTemp(request.getStationName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.warn("Could not load the data in detail "+ e.getMessage());
		}
   

        return ResponseEntity.ok(result);

    }

   
}
