package com.zooplus.service.impl;

import static org.junit.Assert.fail;

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
		
		
		List<Currency> currencies = curencyService.getCurrencies();
		ConversionQuery conversionQuery = new ConversionQuery();
		conversionQuery.setCurrencyFrom(currencies.get(0));
		conversionQuery.setCurrencyTo(currencies.get(1));
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		conversionQuery.setDate(calendar.getTime());
		conversionQuery = exchangeService.exchange(conversionQuery);
		
		Assert.notNull(conversionQuery.getId());
		Assert.notEmpty(exchangeService.getQueries());
	}

}
