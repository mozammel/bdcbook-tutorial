package com.bdcyclists.bdcbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdcyclists.bdcbook.domain.User;
import com.bdcyclists.bdcbook.dto.RegistrationForm;
import com.bdcyclists.bdcbook.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository repository;

	@Autowired
	public UserServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public User findByEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public User registerNewUserAccount(RegistrationForm registrationForm) throws DuplicateEmailException {
		
		if( repository.findByEmail(registrationForm.getEmail()) != null ) {
			throw new DuplicateEmailException("The email address: " + registrationForm.getEmail()
					+ " is already in use.");
		}
		
		User.Builder user = User.getBuilder()
				.email(registrationForm.getEmail())
				.firstName(registrationForm.getFirstName())
				.lastName(registrationForm.getLastName())
				.password(registrationForm.getPassword());
				
		User registered = user.build();
		
		
		
		return repository.save(registered);
	}
	
	
	
}
