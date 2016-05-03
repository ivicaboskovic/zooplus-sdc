package com.zooplus.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.zooplus.dao.CurrencyDAO;
import com.zooplus.persistence.model.Currency;

@Repository("currencyDAO")
public class CurrencyDAOImpl extends GenericDAOImpl<Currency> implements CurrencyDAO {
	
	@Override
	public List<Currency> getCurrencies() {
		TypedQuery<Currency> query = em.createNamedQuery(Currency.FIND_CURRENCIES, Currency.class);
		return query.getResultList();
	}
}
