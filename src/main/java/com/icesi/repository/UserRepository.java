package com.icesi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icesi.model.User;


public interface UserRepository extends  JpaRepository<User, Integer>{

	
	public User findByUsername(String username);
}
