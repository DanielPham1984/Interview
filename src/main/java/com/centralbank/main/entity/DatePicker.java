package com.centralbank.main.entity;

import  java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
public class DatePicker {
	static final String DATE_FORMAT = "yyyy-MM-dd";
	@DateTimeFormat(pattern = DATE_FORMAT)
	public  Date longDate;
	@DateTimeFormat(pattern = DATE_FORMAT)
	public  Date  startDate;
	@DateTimeFormat(pattern = DATE_FORMAT)
	public  Date  endDate;
	public  Date  getLongDate() {
		return longDate;
	}
	public void setLongDate(Date longDate) {
		this.longDate = longDate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public  Date  getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
