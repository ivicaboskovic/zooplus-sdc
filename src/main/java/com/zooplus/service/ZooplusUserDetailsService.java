package com.zooplus.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zooplus.persistence.model.User;

@Service("zooplusUserDetailsService")
public class ZooplusUserDetailsService implements UserDetailsService {

	@Autowired
	UserService userService;
	
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userService.findByEmail(username);
		if (user!=null){
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
		}else 
			{
			throw new UsernameNotFoundException("Username Not Found");
			//return null;
			}
	}

}
