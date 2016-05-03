package com.zooplus.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.zooplus.dao.ConversionQueryDAO;
import com.zooplus.persistence.model.ConversionQuery;

@Repository("conversionQueryDAO")
public class ConversionQueryDAOImpl extends GenericDAOImpl<ConversionQuery> implements ConversionQueryDAO {
	
	@Override
	public List<ConversionQuery> getQueries() {
		TypedQuery<ConversionQuery> query = em.createNamedQuery(ConversionQuery.FIND_QUERIES, ConversionQuery.class);
		query.setMaxResults(10);
		return query.getResultList();
	}
}
