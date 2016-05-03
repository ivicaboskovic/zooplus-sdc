package com.zooplus.service;

import com.zooplus.persistence.model.User;

public interface UserService {

	User findByEmail(String email);

	User save(User user);

	boolean userWithEmailExists(User user);

}
