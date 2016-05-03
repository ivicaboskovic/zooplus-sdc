package com.zooplus.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.zooplus.persistence.model.Country;
import com.zooplus.persistence.model.Currency;
import com.zooplus.persistence.model.User;
import com.zooplus.propertyeditor.EntityPropertyEditor;
import com.zooplus.service.GenericService;


public abstract class BaseController {
	
	private final static Logger LOG = LoggerFactory.getLogger(BaseController.class);		
	
	@Autowired 
	GenericService genericService;

	
	@InitBinder
	public void initBinder(WebDataBinder binder) {		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		binder.registerCustomEditor(Country.class, new EntityPropertyEditor(Country.class, genericService));
		binder.registerCustomEditor(Currency.class, new EntityPropertyEditor(Currency.class, genericService));

	}

	protected User getLoggedUser(HttpServletRequest httpRequest) {
		return (User) httpRequest.getSession().getAttribute("loggedUser");
	}
	
	protected void setLoggedUser(HttpServletRequest httpRequest, User user) {
		httpRequest.getSession().setAttribute("loggedUser",user);
	}

}
