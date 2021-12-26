package com.icesi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icesi.model.Businessentity;

public interface BusinessEntityRepository extends JpaRepository<Businessentity, Long> {
	

}
