package com.zooplus.dao.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.zooplus.dao.UserDAO;
import com.zooplus.persistence.model.User;

@Repository("userDAO")
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {
	
	@Override
	public User getUserByEmail(String email) {
		Query query = em.createNamedQuery(User.FIND_USER_BY_EMAIL, User.class);
		query.setParameter("email", email);
		List<User> users = query.getResultList();
		if (!users.isEmpty()){
			return users.get(0);
		}else{
			return null;
		}
	}

	
	@Override
	public boolean userWithEmailExists(User user) {
		TypedQuery<User> query = em.createNamedQuery(User.FIND_USER_BY_EMAIL, User.class);
		query.setParameter("email", user.getEmail());
		List<User> users = query.getResultList();
		if (!users.isEmpty()){
			if (user.getId()!=null){
				return !users.get(0).getId().equals(user.getId());
			}else{
				return true;
			}
		}else{
			return false;
		}
	}
		
	@Override
	public User saveOrUpdate(User user){
		if (user.getId()==null){
			return create(user);
		}else{
			return update(user);
		}
	}
}
