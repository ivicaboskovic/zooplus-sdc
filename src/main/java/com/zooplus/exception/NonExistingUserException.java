package com.zooplus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NonExistingUserException  extends BadCredentialsException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6277781145019199440L;

	public NonExistingUserException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
