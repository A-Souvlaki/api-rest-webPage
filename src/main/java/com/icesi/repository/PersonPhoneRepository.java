package com.icesi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icesi.model.Personphone;



public interface PersonPhoneRepository extends JpaRepository<Personphone, Integer> {
	
	
}
