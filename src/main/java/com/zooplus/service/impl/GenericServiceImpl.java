package com.zooplus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zooplus.dao.CommonDAO;
import com.zooplus.persistence.model.BaseEntity;
import com.zooplus.service.GenericService;

@Service("genericService")
public class GenericServiceImpl implements GenericService {
	
	@Autowired
	private CommonDAO commonDAO;

	@Override
	public BaseEntity<?> findById(Class<?> c, Long id) {
		return commonDAO.findById(c, id);
	}
	
	
	@Override
	public List<BaseEntity<?>> executeQuery(String query, final List<?> queryArgs) {
	     return commonDAO.executeQuery(query, queryArgs);
	 }

}
