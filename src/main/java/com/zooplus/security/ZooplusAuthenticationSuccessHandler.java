package com.zooplus.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.zooplus.persistence.model.User;
import com.zooplus.service.UserService;


public class ZooplusAuthenticationSuccessHandler extends	SavedRequestAwareAuthenticationSuccessHandler {
	
	@Autowired
	UserService userService;
	
	 @Override
	    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
	    Authentication authentication) throws ServletException, IOException {
	        // log authentication success here for both cases
	        super.onAuthenticationSuccess(request, response, authentication);
	        if (authentication  instanceof RememberMeAuthenticationToken){
	        	User user = userService.findByEmail(((UserDetails)authentication.getPrincipal()).getUsername());
	        	request.getSession().setAttribute("loggedUser", user);
	        }else{
	        	User user  = (User) authentication.getDetails();
	        	request.getSession().setAttribute("loggedUser", user);
	        }
	    }
}
