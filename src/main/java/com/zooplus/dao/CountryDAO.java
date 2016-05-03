package com.zooplus.dao;

import java.util.List;

import com.zooplus.persistence.model.Country;

public interface CountryDAO extends GenericDAO<Country> {

	List<Country> getCountries();

}
