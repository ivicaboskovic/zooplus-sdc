package com.zooplus.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zooplus.persistence.model.ConversionQuery;

@Controller
public class ExchangeController extends BaseController{
	
	@Autowired
    @Qualifier("conversionQueryValidator")
    private Validator validator;
 
    @InitBinder
	public void initBinder(WebDataBinder binder) {
    	super.initBinder(binder);
        binder.addValidators(validator);
    }
	
	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	@Secured({"ROLE_USER"})
	public String adminIndex(Model model){
		model.addAttribute("conversionQuery", new ConversionQuery());
		return "index";
	}
	
	@RequestMapping(value = "/convert", method = RequestMethod.POST)
	@Secured({"ROLE_USER"})
	public String convert(@Valid ConversionQuery conversionQuery, BindingResult bindingResult) {
		if(bindingResult.hasErrors()){
			return "index";
		}else{
			return "index";
		}
	}	
}
