package com.bdcyclists.bdcbook.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bdcyclists.bdcbook.dto.RegistrationForm;

@Controller
public class RegistrationController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);
	
	@RequestMapping(value="/user/register", method = RequestMethod.GET)
	public String showRegistrationPage(RegistrationForm registrationForm) {
		LOGGER.debug("Rendering RegistrationPage.");
		return "registration";
	}
	
	
	@RequestMapping(value="/user/register", method = RequestMethod.POST)
	public String registerUserAccount(@Valid RegistrationForm registrationForm,
			BindingResult result) {
		System.out.println("From RegistrationController: " + registrationForm);
		return "registration";
	}

}
