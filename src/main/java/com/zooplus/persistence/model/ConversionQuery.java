package com.zooplus.persistence.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@NamedQueries({ 
	@NamedQuery(name = ConversionQuery.FIND_QUERIES, query = "SELECT c FROM ConversionQuery c WHERE c.user = :user order by c.created DESC"),

})
public class ConversionQuery extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3822979774626804325L;
	
	public static final String FIND_QUERIES = "FIND_QUERIES";
	
	@NotNull
	@ManyToOne
	private Currency currencyFrom;
	
	@NotNull
	@ManyToOne
	private Currency currencyTo;
	
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Past
    @NotNull
    private Date date;
	
	
	private Double rate;
	
	@ManyToOne
	private User user;


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


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	

}
