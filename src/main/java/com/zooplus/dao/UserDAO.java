package com.zooplus.dao;

import com.zooplus.persistence.model.User;

public interface UserDAO extends GenericDAO<User> {

	User getUserByEmail(String email);

	User saveOrUpdate(User user);

	boolean userWithEmailExists(User user);

}
