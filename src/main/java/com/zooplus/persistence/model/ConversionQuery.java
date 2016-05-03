package com.zooplus.persistence.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class ConversionQuery extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3822979774626804325L;
	
	@NotNull
	private Currency currencyFrom;
	
	@NotNull
	private Currency currencyTo;
	
	
	@DateTimeFormat(pattern="MM/dd/yyyy")
    @NotNull
    private Date date;
	
	
	private Double rate;


	public Currency getCurrencyFrom() {
		return currencyFrom;
	}


	public void setCurrencyFrom(Currency currencyFrom) {
		this.currencyFrom = currencyFrom;
	}


	public Currency getCurrencyTo() {
		return currencyTo;
	}


	public void setCurrencyTo(Currency currencyTo) {
		this.currencyTo = currencyTo;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Double getRate() {
		return rate;
	}


	public void setRate(Double rate) {
		this.rate = rate;
	}
	
	

}
