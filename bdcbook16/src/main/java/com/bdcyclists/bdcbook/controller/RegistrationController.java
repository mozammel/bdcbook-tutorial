package com.bdcyclists.bdcbook.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bdcyclists.bdcbook.domain.User;
import com.bdcyclists.bdcbook.dto.RegistrationForm;
import com.bdcyclists.bdcbook.service.UserService;

@Controller
public class RegistrationController {
	
	@Autowired
	public UserService userService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);
	
	protected static final String VIEW_NAME_REGISTRATION_PAGE = "user/registration";
	
	@RequestMapping(value="/user/register", method = RequestMethod.GET)
	public String showRegistrationPage(RegistrationForm registrationForm) {
		System.out.println("from controller");
		LOGGER.debug("Rendering RegistrationPage.");
		return VIEW_NAME_REGISTRATION_PAGE;
	}
	
	
	@RequestMapping(value="/user/register", method = RequestMethod.POST)
	public String registerUserAccount(@Valid RegistrationForm registrationForm,
			BindingResult result) {

		if(result.hasErrors()) {
			return VIEW_NAME_REGISTRATION_PAGE;
		}
		
		User registered = createUserAccount(registrationForm, result);
		
		if( registered == null ) {
			LOGGER.debug("An email address was found from the database. Rendering the view.");
			return VIEW_NAME_REGISTRATION_PAGE;
		}
		
		LOGGER.debug("Registered user account with information: {}", registered);
		
		return "success";
	}


	private User createUserAccount(RegistrationForm registrationForm,
			BindingResult result) {
		User registered = null;
		
		registered = userService.registerNewUserAccount(registrationForm);
		
		return registered;
	}

}
