package com.bdcyclists.bdcbook.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String showRegistrationPage() {
		LOGGER.debug("Rendering RegistrationPage.");
		return "registration";
	}
	

}
