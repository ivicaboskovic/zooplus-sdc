package com.zooplus.exception;

import org.springframework.security.authentication.BadCredentialsException;

public class InactiveUserException  extends BadCredentialsException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6277781145019199440L;

	public InactiveUserException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
