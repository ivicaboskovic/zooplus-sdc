package com.zooplus.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.zooplus.dao.CommonDAO;
import com.zooplus.persistence.model.BaseEntity;


@Repository("commonDAO")
public class CommonDAOImpl implements CommonDAO {

    @PersistenceContext
    protected EntityManager em;


	@Override
	public BaseEntity<?> findById(Class<?> cl, Long id) {
		// TODO Auto-generated method stub
		return (BaseEntity<?>)em.find(cl, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BaseEntity<?>> executeQuery(String query, final List<?> queryArgs) {
	     final Query namedQuery = em.createNamedQuery(query);
	     for(int i = 0; queryArgs!=null && i < queryArgs.size(); i++) {
	             namedQuery.setParameter(i+1, queryArgs.get(i));
	      }
	      return (List<BaseEntity<?>>) namedQuery.getResultList();
	 }
}