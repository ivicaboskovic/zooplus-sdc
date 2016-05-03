package com.zooplus.util;


import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

import com.zooplus.controller.dto.ExchangeResponse;

public class ExchangeUtilTest {

	@Test
	public void testCalculateRate() {
		ExchangeResponse response = new ExchangeResponse();
		response.setBase("USD");
		response.setRates(new HashMap<String, Double>());
		response.getRates().put("EUR", 100D);
		response.getRates().put("GBP", 200D);
		assertEquals(100D, ExchangeUtil.calculateRate(response, "USD", "EUR").doubleValue(),0d);
		assertEquals(200D, ExchangeUtil.calculateRate(response, "USD", "GBP").doubleValue(),0d);
		assertEquals(0.5D, ExchangeUtil.calculateRate(response, "EUR", "GBP").doubleValue(),0d);
	}

}
