package com.zooplus.service.impl;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.zooplus.persistence.model.Country;
import com.zooplus.persistence.model.User;
import com.zooplus.persistence.model.User.Role;
import com.zooplus.service.UserService;
import com.zooplus.util.ZooplusStringUtil;

@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration({ "classpath:test-context.xml" })
@Transactional  
@RunWith(SpringJUnit4ClassRunner.class)  
public class UserServiceImplTest {
	
	@Autowired
	UserService userService;

	@Test
	public void testFindByEmail() {
		Assert.notNull(userService.findByEmail("test.user@zooplus.com"));
	}

	@Test
	public void testSave() {
		User user2 = new User();
		user2.setEmail("test.user@zooplus.com");
		Assert.isTrue(userService.userWithEmailExists(user2));
		
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
		user2 = userService.save(user2);
		
		Assert.notNull(user2.getId());
	}

	@Test
	public void testUserWithEmailExists() {
		User user  = userService.findByEmail("test.user@zooplus.com");
		Assert.isTrue(!userService.userWithEmailExists(user));
	}

}
