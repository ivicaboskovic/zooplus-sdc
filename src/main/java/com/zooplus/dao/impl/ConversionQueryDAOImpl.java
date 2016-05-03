package com.zooplus.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.zooplus.dao.ConversionQueryDAO;
import com.zooplus.persistence.model.ConversionQuery;
import com.zooplus.persistence.model.User;

@Repository("conversionQueryDAO")
public class ConversionQueryDAOImpl extends GenericDAOImpl<ConversionQuery> implements ConversionQueryDAO {
	
	@Override
	public List<ConversionQuery> getQueries(User user) {
		TypedQuery<ConversionQuery> query = em.createNamedQuery(ConversionQuery.FIND_QUERIES, ConversionQuery.class);
		query.setParameter("user", user);
		query.setMaxResults(10);
		return query.getResultList();
	}
}
