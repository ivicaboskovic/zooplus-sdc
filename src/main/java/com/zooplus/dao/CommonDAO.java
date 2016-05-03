package com.zooplus.dao;

import java.util.List;

import com.zooplus.persistence.model.BaseEntity;

public interface CommonDAO {

	BaseEntity<?> findById(Class<?> cl, Long id);
	
	List<BaseEntity<?>> executeQuery(String query, List<?> queryArgs);
}
