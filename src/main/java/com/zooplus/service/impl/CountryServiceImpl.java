package com.zooplus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zooplus.dao.CountryDAO;
import com.zooplus.persistence.model.Country;
import com.zooplus.service.CountryService;

@Service("countryService")
public class CountryServiceImpl implements CountryService {
	
	@Autowired
	CountryDAO countryDAO;
	
	@Override
	public List<Country> getCountries() {
		return countryDAO.getCountries();
	}
	
}
