package com.zooplus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zooplus.dao.CurrencyDAO;
import com.zooplus.persistence.model.Currency;
import com.zooplus.service.CurrencyService;

@Service("currencyService")
public class CurrencyServiceImpl implements CurrencyService {
	
	@Autowired
	CurrencyDAO currencyDAO;
	
	@Override
	public List<Currency> getCurrencies() {
		return currencyDAO.getCurrencies();
	}
	
}
