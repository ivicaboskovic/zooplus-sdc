package com.zooplus.dao;

import java.util.List;

import com.zooplus.persistence.model.ConversionQuery;
import com.zooplus.persistence.model.User;

public interface ConversionQueryDAO extends GenericDAO<ConversionQuery>{

	List<ConversionQuery> getQueries(User user);

}
