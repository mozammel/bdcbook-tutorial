package com.bdcyclists.bdcbook.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bdcyclists.bdcbook.domain.User;
import com.bdcyclists.bdcbook.dto.RegistrationForm;
import com.bdcyclists.bdcbook.service.DuplicateEmailException;
import com.bdcyclists.bdcbook.service.UserService;

@Controller
public class RegistrationController {
	
	@Autowired
	public UserService userService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);
	
	protected static final String VIEW_NAME_REGISTRATION_PAGE = "user/registration";
    protected static final String ERROR_CODE_EMAIL_EXIST = "NotExist.user.email";
    protected static final String MODEL_NAME_REGISTRATION_DTO = "registrationForm";
	
	@RequestMapping(value="/user/register", method = RequestMethod.GET)
	public String showRegistrationPage(RegistrationForm registrationForm) {
		System.out.println("from controller");
		LOGGER.debug("Rendering RegistrationPage.");
		return VIEW_NAME_REGISTRATION_PAGE;
	}
	
	
	@RequestMapping(value="/user/register", method = RequestMethod.POST)
	public String registerUserAccount(@Valid RegistrationForm registrationForm,
			BindingResult result) {
        LOGGER.debug("Registering user account with information: {}", registrationForm);
		if(result.hasErrors()) {
            LOGGER.debug("Validation errors found. Rendering form view.");
			return VIEW_NAME_REGISTRATION_PAGE;
		}

		LOGGER.debug("No validation errors found. Continuing registration process.");
		
		User registered = createUserAccount(registrationForm, result);
		
        //If email address was already found from the database, render the form view.
		if( registered == null ) {
			LOGGER.debug("An email address was found from the database. Rendering the view.");
			return VIEW_NAME_REGISTRATION_PAGE;
		}
		
		LOGGER.debug("Registered user account with information: {}", registered);
		
		return "success";
	}


	private User createUserAccount(RegistrationForm registrationForm,
			BindingResult result) {
        LOGGER.debug("Creating user account with information: {}", registrationForm);
        User registered = null;
		
		try {
			registered = userService.registerNewUserAccount(registrationForm);
		} catch (DuplicateEmailException e) {
	           LOGGER.debug("An email address: {} exists.", registrationForm.getEmail());
	           addFieldError(MODEL_NAME_REGISTRATION_DTO, RegistrationForm.FIELD_NAME_EMAIL, registrationForm.getEmail(),
	                    ERROR_CODE_EMAIL_EXIST, result);
	  		}
		
		return registered;
	}

	private void addFieldError(String objectName, String fieldName, String fieldValue, String errorCode, BindingResult result) {
        LOGGER.debug("Adding field error object's: {} field: {} with error code: {}", objectName, fieldName, errorCode);

        FieldError error = new FieldError(
                objectName,
                fieldName,
                fieldValue,
                false,
                new String[]{errorCode},
                new Object[]{},
                errorCode
        );

        result.addError(error);
        LOGGER.debug("Added field error: {} to binding result: {}", error, result);
    }


}
