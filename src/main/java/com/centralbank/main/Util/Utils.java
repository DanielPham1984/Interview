package com.centralbank.main.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.centralbank.main.entity.ClimateObj;

public class Utils {
	 public static ClimateObj getDetailBaseOnMeanTemp(String name) throws IOException {
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
	 public static  List<ClimateObj> loadCSVDataWithinRange(long startDate, long endDate) throws IOException, ParseException {
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
	    
	 public static  List<ClimateObj> loadCSVData() throws IOException {
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
