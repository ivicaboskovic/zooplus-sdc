package com.zooplus.service;

import java.util.List;

import com.zooplus.persistence.model.ConversionQuery;
import com.zooplus.persistence.model.User;


public interface ExchangeService {

	ConversionQuery exchange(ConversionQuery conversionQuery);

	List<ConversionQuery> getQueries(User user);

}
