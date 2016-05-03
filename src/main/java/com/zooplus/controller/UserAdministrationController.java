package com.zooplus.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zooplus.persistence.model.User;
import com.zooplus.service.CountryService;
import com.zooplus.service.UserService;
import com.zooplus.util.ZooplusStringUtil;

@Controller
public class UserAdministrationController extends BaseController{
	
	@Autowired
	UserService userService;
	
	@Autowired
	CountryService countryService;
	
	@Autowired
    @Qualifier("userValidator")
    private Validator validator;
 
    @InitBinder
	public void initBinder(WebDataBinder binder) {
    	super.initBinder(binder);
        binder.addValidators(validator);
    }
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }


	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String createUser(Model model) {
		model.addAttribute("user", new User());
		return "edit-user";
	}
	
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult bindingResult) {
		if(bindingResult.hasErrors()){
			return "edit-user";
		}else{
			user.setPassword(ZooplusStringUtil.encryptPassword(user.getPassword()));
			userService.save(user);
			return "redirect:/login";
		}
	}	
}
