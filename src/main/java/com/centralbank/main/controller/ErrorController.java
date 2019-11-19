package com.centralbank.main.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/*****************************
 * 
 * @author An Pham
 *
 */

public class ErrorController {
	   final public static String ERROR_VIEW = "error";
	   @RequestMapping(value = "errors", method = RequestMethod.GET)
	    public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {
	        ModelAndView errorPage = new ModelAndView(ERROR_VIEW);
	        String errorMsg = "";
	        int httpErrorCode = getErrorCode(httpRequest);
	 
	        switch (httpErrorCode) {
	            case 400: {
	                errorMsg = "Http Error Code: 400. Bad Request";
	                break;
	            }
	            case 401: {
	                errorMsg = "Http Error Code: 401. Unauthorized";
	                break;
	            }
	            case 404: {
	                errorMsg = "Http Error Code: 404. Resource not found";
	                break;
	            }
	            case 500: {
	                errorMsg = "Http Error Code: 500. Internal Server Error";
	                break;
	            }
	        }
	        errorPage.addObject("errorMsg", errorMsg);
	        return errorPage;
	    }
	     
	    private int getErrorCode(HttpServletRequest httpRequest) {
	        return (Integer) httpRequest
	          .getAttribute("javax.servlet.error.status_code");
	    }
}
