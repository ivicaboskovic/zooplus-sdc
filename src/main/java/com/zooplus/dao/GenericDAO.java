package com.zooplus.dao;

import java.util.List;

import org.hibernate.Criteria;

public interface GenericDAO<T> {

    T create(T t);

    void delete(Object id);

    T find(Object id);

    T update(T t);
    
	List<T> findByCriteria(Criteria criteria);	
}