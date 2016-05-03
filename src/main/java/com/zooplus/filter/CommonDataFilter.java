package com.zooplus.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.ContextExposingHttpServletRequest;

import com.zooplus.service.CountryService;
import com.zooplus.service.CurrencyService;


@Component
public class CommonDataFilter implements Filter, ApplicationContextAware {

	
	private static final String CURRENCIES = "currencies";

	private static final String COUNTRIES = "countries";

	private ApplicationContext applicationContext;

	private static final Logger log = LoggerFactory.getLogger(CommonDataFilter.class);
	
	

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	private List<?> nameToDataList(HttpServletRequest wrappedRequest, String name) {
		List<?> result = new ArrayList<>();
		try {
			if (COUNTRIES.equalsIgnoreCase(name)) {
				CountryService countryService = (CountryService) wrappedRequest.getAttribute("countryService");
				return countryService.getCountries();
			} else if (CURRENCIES.equalsIgnoreCase(name)) {
				CurrencyService countryService = (CurrencyService) wrappedRequest.getAttribute("currencyService");
				return countryService.getCurrencies();
			} 
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return result;
	}
	
	private void initalizeListRequestAttributeFromSession(HttpServletRequest wrappedRequest, String name) {
		HttpSession session = wrappedRequest.getSession(true);
		if (session.getAttribute(name) == null) {
			session.setAttribute(name, nameToDataList(wrappedRequest, name));
		}
		wrappedRequest.setAttribute(name, session.getAttribute(name));
	}
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			WebApplicationContext webApplicationContext = (WebApplicationContext) applicationContext;
			HttpServletRequest wrappedRequest = new ContextExposingHttpServletRequest(httpRequest, webApplicationContext);
		try {
			initalizeListRequestAttributeFromSession(wrappedRequest, COUNTRIES);
			initalizeListRequestAttributeFromSession(wrappedRequest, CURRENCIES);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		chain.doFilter(wrappedRequest, response);
		
	}
	
	
	

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext = applicationContext;
		
	}

}
