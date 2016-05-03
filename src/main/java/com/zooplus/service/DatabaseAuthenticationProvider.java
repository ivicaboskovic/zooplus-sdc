package com.zooplus.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.zooplus.exception.NonExistingUserException;
import com.zooplus.persistence.model.User;
import com.zooplus.util.ZooplusStringUtil;

@Service("databaseAuthenticationProvider")
public class DatabaseAuthenticationProvider implements AuthenticationProvider {

	private final static Logger LOG = LoggerFactory.getLogger(DatabaseAuthenticationProvider.class);
	@Autowired
	private UserService userService;
	
	
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {


			return authenticate(authentication, true);

	}
	
	public Authentication authenticate(Authentication authentication, boolean checkStatus)
			throws AuthenticationException {


			UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;

			String email = (String) authToken.getPrincipal();
			String password = (String) authToken.getCredentials();

			User loggedUser = userService.findByEmail(email);
			if (loggedUser != null) {
				if (!loggedUser.getPassword().equals(ZooplusStringUtil.encryptPassword(password))){
					throw new BadCredentialsException("bad username or password");
				}
				List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				authorities.add(new SimpleGrantedAuthority(loggedUser.getRole().name()));
				UsernamePasswordAuthenticationToken succeedToken = new UsernamePasswordAuthenticationToken(loggedUser.getEmail(), ZooplusStringUtil.encryptPassword(password), authorities);
				succeedToken.setDetails(loggedUser);
				return succeedToken;
			}else {
				throw new NonExistingUserException("not exists");
			}

	}

	public boolean supports(Class<? extends Object> authentication) {
		boolean supports = UsernamePasswordAuthenticationToken.class
				.isAssignableFrom(authentication);
		return supports;
	}

}