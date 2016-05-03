package com.zooplus.service;

import java.util.List;

import com.zooplus.persistence.model.ConversionQuery;


public interface ExchangeService {

	ConversionQuery exchange(ConversionQuery conversionQuery);

	List<ConversionQuery> getQueries();

}
