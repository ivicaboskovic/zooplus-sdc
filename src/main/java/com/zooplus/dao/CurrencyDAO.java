package com.zooplus.dao;

import java.util.List;

import com.zooplus.persistence.model.Currency;

public interface CurrencyDAO extends GenericDAO<Currency> {

	List<Currency> getCurrencies();

}
