package com.zooplus.dao;

import java.util.List;

import com.zooplus.persistence.model.ConversionQuery;

public interface ConversionQueryDAO extends GenericDAO<ConversionQuery>{

	List<ConversionQuery> getQueries();

}
