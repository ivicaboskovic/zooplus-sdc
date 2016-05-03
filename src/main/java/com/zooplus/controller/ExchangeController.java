package com.zooplus.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import com.zooplus.persistence.model.ConversionQuery;
import com.zooplus.service.ExchangeService;

@Controller
public class ExchangeController extends BaseController{
	
	private final static Logger LOG = LoggerFactory.getLogger(ExchangeController.class);	
	
	@Autowired
    @Qualifier("conversionQueryValidator")
    private Validator validator;
	
	@Autowired
	ExchangeService exchangeService;
	
	@Autowired
    private MessageSource messageSource;
 
    @InitBinder
	public void initBinder(WebDataBinder binder) {
    	super.initBinder(binder);
        binder.addValidators(validator);
    }
	
	@RequestMapping(value = {"/index.html", "/convert"}, method = RequestMethod.GET)
	@Secured({"ROLE_USER"})
	public String adminIndex(Model model, HttpServletRequest request){
		model.addAttribute("conversionQuery", new ConversionQuery());
		model.addAttribute("queries", exchangeService.getQueries(getLoggedUser(request)));
		return "index";
	}
	
	@RequestMapping(value = "/convert", method = RequestMethod.POST)
	@Secured({"ROLE_USER"})
	public String convert(@Valid ConversionQuery conversionQuery, BindingResult bindingResult, HttpServletRequest request, Locale locale, Model model) {
		if(bindingResult.hasErrors()){
			return "index";
		}else{
			try {
				conversionQuery.setUser(getLoggedUser(request));
				conversionQuery = exchangeService.exchange(conversionQuery);
				if (conversionQuery!=null){
					request.setAttribute("successMessage", messageSource.getMessage("convert.currency.success", new String[] {conversionQuery.getCurrencyFrom().getCode() + " " +conversionQuery.getCurrencyFrom().getName(), conversionQuery.getCurrencyTo().getCode(), conversionQuery.getRate().toString()}, locale));
				}else{
					request.setAttribute("errorMessage", messageSource.getMessage("convert.currency.nodata", new String[]{}, locale));
				}
			}catch (HttpClientErrorException e){
				request.setAttribute("errorMessage", messageSource.getMessage("convert.currency.nodata", new String[]{}, locale));
			}catch (RestClientException e){
				request.setAttribute("errorMessage", messageSource.getMessage("convert.currency.error", new String[]{}, locale));
			}
			model.addAttribute("queries", exchangeService.getQueries(getLoggedUser(request)));
			return "index";
		}
	}	
}
