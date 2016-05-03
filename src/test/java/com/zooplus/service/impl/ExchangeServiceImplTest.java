package com.zooplus.service.impl;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.zooplus.persistence.model.ConversionQuery;
import com.zooplus.persistence.model.Currency;
import com.zooplus.persistence.model.User;
import com.zooplus.service.CurrencyService;
import com.zooplus.service.ExchangeService;

@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration({ "classpath:test-context.xml" })
@Transactional  
@RunWith(SpringJUnit4ClassRunner.class)  
public class ExchangeServiceImplTest {
	
	@Autowired
	ExchangeService exchangeService;
	
	@Autowired
	CurrencyService curencyService;
	
	

	@Test
	public void testExchange() {
		
		User user = new User();
		user.setId(1l);
		List<Currency> currencies = curencyService.getCurrencies();
		ConversionQuery conversionQuery = new ConversionQuery();
		conversionQuery.setCurrencyFrom(currencies.get(0));
		conversionQuery.setCurrencyTo(currencies.get(1));
		conversionQuery.setUser(user);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2015);
		calendar.set(Calendar.MONTH, 4);
		calendar.set(Calendar.DATE, 1);
		conversionQuery.setDate(calendar.getTime());
		conversionQuery = exchangeService.exchange(conversionQuery);
		
		
		Assert.notNull(conversionQuery.getId());
		Assert.notEmpty(exchangeService.getQueries(user));
	}

}
