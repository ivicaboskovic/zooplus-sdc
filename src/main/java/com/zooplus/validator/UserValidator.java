package com.zooplus.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.zooplus.persistence.model.User;
import com.zooplus.service.UserService;

public class UserValidator implements Validator {
	
	@Autowired
	UserService userService;

	@Override
	public boolean supports(Class<?> paramClass) {
		return User.class.equals(paramClass);
	}

	@Override
	public void validate(Object object, Errors errors) {
		User user = (User)object;
		if (user.getPassword()!=null && user.getPasswordRetype()!=null){
			if (!user.getPassword().equals(user.getPasswordRetype())){
				errors.rejectValue("password", "user.passwors.notSame");
			}
		}
		if (StringUtils.isNotBlank(user.getEmail())){
			if (userService.userWithEmailExists(user)){
				errors.rejectValue("email", "user.email.exists");
			}
		}
	}
}