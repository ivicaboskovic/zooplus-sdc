package com.zooplus.service;

import java.util.List;

import com.zooplus.persistence.model.BaseEntity;

public interface GenericService {
	BaseEntity<?> findById(Class<?> c, Long id);

	List<BaseEntity<?>> executeQuery(String query, List<?> queryArgs);
}
