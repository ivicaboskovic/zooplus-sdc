package com.zooplus.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.zooplus.service.ZooplusUserDetailsService;


@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration({ "classpath:test-context.xml" })
@Transactional  
@RunWith(SpringJUnit4ClassRunner.class)  
public class ZooplusUserDetailsServiceTest {
	
	@Autowired
	ZooplusUserDetailsService zooplusUserDetailsService;

	@Test
	public void testLoadUserByUsername() {
		Assert.notNull(zooplusUserDetailsService.loadUserByUsername("test.user@zooplus.com"));
	}
	
	@Test(expected = UsernameNotFoundException.class)
	public void testLoadUserByUsernameException() {
		Assert.notNull(zooplusUserDetailsService.loadUserByUsername("test22.user@zooplus.com"));
	}

}
