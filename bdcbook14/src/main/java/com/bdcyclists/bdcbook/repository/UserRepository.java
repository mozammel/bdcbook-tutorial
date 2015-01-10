package com.bdcyclists.bdcbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bdcyclists.bdcbook.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
	public User findByEmail(String email);
}
