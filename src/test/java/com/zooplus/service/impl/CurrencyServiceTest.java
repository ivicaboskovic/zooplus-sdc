package com.zooplus.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.zooplus.service.CurrencyService;

@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration({ "classpath:test-context.xml" })
@Transactional  
@RunWith(SpringJUnit4ClassRunner.class)  
public class CurrencyServiceTest {

	@Autowired
	CurrencyService currencyService;
	
	@Test
	public void testGetCurrencies() {
		Assert.notEmpty(currencyService.getCurrencies());
	}

}
