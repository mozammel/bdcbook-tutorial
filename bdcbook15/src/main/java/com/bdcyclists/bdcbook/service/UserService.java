package com.bdcyclists.bdcbook.service;

import com.bdcyclists.bdcbook.domain.User;

public interface UserService {

	public User findByEmail(String email);
}
