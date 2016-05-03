package com.zooplus.controller.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class ExchangeResponse implements Serializable{
	
	private String disclaimer;
	
	private String license;
	
	private Date timestamp;
	
	private String base;
	
	private HashMap<String, Double> rates;

	public String getDisclaimer() {
		return disclaimer;
	}

	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public HashMap<String, Double> getRates() {
		return rates;
	}

	public void setRates(HashMap<String, Double> rates) {
		this.rates = rates;
	}
	
	

}
