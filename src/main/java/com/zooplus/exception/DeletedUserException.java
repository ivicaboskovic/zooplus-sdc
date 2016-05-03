package com.zooplus.exception;

import org.springframework.security.authentication.BadCredentialsException;

public class DeletedUserException extends BadCredentialsException {

	public DeletedUserException(String msg) {
		super(msg);
	}
}
