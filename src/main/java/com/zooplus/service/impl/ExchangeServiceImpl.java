package com.zooplus.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.zooplus.controller.dto.ExchangeResponse;
import com.zooplus.dao.ConversionQueryDAO;
import com.zooplus.persistence.model.ConversionQuery;
import com.zooplus.service.ExchangeService;
import com.zooplus.util.ExchangeUtil;
import com.zooplus.util.ZooplusStringUtil;

@Service
public class ExchangeServiceImpl implements ExchangeService{
	
	private static final String EXCHANGE_URL = "https://openexchangerates.org/api/historical/";

	private RestTemplate restTemplate = new RestTemplate();
	
	private final static Logger LOG = LoggerFactory.getLogger(ExchangeServiceImpl.class);
	
	@Autowired
	ConversionQueryDAO conversionQueryDAO;
	
	
	@Override
	@Transactional(readOnly = false)
	public ConversionQuery exchange(ConversionQuery conversionQuery)  throws RestClientException{
		return conversionQueryDAO.create(calculateRate(conversionQuery));
	}
	
	@Override
	public List<ConversionQuery> getQueries(){
		return conversionQueryDAO.getQueries();
	}
	
	
	private ResponseEntity<ExchangeResponse> getConversion(ConversionQuery conversionQuery) throws RestClientException{
		
		ResponseEntity<ExchangeResponse> responseEntity = null;
		responseEntity = restTemplate.getForEntity(EXCHANGE_URL + ZooplusStringUtil.formatDate(conversionQuery.getDate()) + ".json?app_id=058572d938f246778e9552560c87cfba", ExchangeResponse.class);
		return responseEntity;
	}
	
	
	private ConversionQuery calculateRate(ConversionQuery conversionQuery)  throws RestClientException{
		ResponseEntity<ExchangeResponse> responseEntity = getConversion(conversionQuery);
		if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
			conversionQuery.setRate(ExchangeUtil.calculateRate(responseEntity.getBody(), conversionQuery.getCurrencyFrom().getCode(), conversionQuery.getCurrencyTo().getCode()));
			return conversionQuery;
		}else{
			LOG.debug("Unable to make conversion from {} to {}", conversionQuery.getCurrencyFrom().getCode(), conversionQuery.getCurrencyTo().getCode());
			return null;
		}
	}

}
