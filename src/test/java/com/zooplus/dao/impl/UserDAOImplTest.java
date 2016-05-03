package com.zooplus.dao.impl;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.zooplus.dao.UserDAO;
import com.zooplus.persistence.model.Country;
import com.zooplus.persistence.model.User;
import com.zooplus.persistence.model.User.Role;
import com.zooplus.util.ZooplusStringUtil;


@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration({ "classpath:test-context.xml" })
@Transactional  
@RunWith(SpringJUnit4ClassRunner.class)  
public class UserDAOImplTest {

	@Autowired
	UserDAO userDAO;
	
	@Test
	public void testEmail() {
		
		User user  = userDAO.getUserByEmail("test.user@zooplus.com");
		Assert.notNull(user);
		
	}
	
	@Test
	public void testEmailExists() {
		User user  = userDAO.getUserByEmail("test.user@zooplus.com");
		Assert.isTrue(!userDAO.userWithEmailExists(user));
	}
	
	@Test
	public void testCreateUser() {
	
		User user2 = new User();
		user2.setEmail("test.user@zooplus.com");
		Assert.isTrue(userDAO.userWithEmailExists(user2));
		
		user2.setEmail("test2.user@zooplus.com");
		Country country = new Country();
		country.setId(1l);
		user2.setBirthday(new Date());
		user2.setCity("beograd");
		user2.setCountry(country);
		user2.setFirstName("11");
		user2.setLastName("22");
		user2.setPassword(ZooplusStringUtil.encryptPassword("1234"));
		user2.setRole(Role.ROLE_USER);
		user2.setStreet("street");
		user2.setZipCode("zipCode");
		user2 = userDAO.saveOrUpdate(user2);
		
		Assert.notNull(user2.getId());
	}

}
