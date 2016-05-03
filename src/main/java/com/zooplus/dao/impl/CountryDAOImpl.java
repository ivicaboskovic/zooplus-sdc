package com.zooplus.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.zooplus.dao.CountryDAO;
import com.zooplus.persistence.model.Country;

@Repository("countryDAO")
public class CountryDAOImpl extends GenericDAOImpl<Country> implements CountryDAO {
	
	@Override
	public List<Country> getCountries() {
		TypedQuery<Country> query = em.createNamedQuery(Country.FIND_COUNTRIES, Country.class);
		return query.getResultList();
	}
}
