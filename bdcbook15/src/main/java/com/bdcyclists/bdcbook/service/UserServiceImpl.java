package com.bdcyclists.bdcbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdcyclists.bdcbook.domain.User;
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
	
	
	
}
