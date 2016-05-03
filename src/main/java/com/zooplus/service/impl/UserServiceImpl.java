package com.zooplus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zooplus.dao.UserDAO;
import com.zooplus.persistence.model.User;
import com.zooplus.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDAO userDAO;
	
	@Override
	public User findByEmail(String email) {
		return userDAO.getUserByEmail(email);
	}
	
	@Override
	@Transactional(readOnly = false)
	public User save(User user){
		return userDAO.saveOrUpdate(user);
	}
	
	@Override
	public boolean userWithEmailExists(User user){
		return userDAO.userWithEmailExists(user);
	}
	
}
