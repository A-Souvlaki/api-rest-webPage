package com.icesi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.model.Businessentity;
import com.icesi.repository.BusinessEntityRepository;

@Service
public class BusinessEntityServiceImp {
	
	private BusinessEntityRepository beRepository;

	@Autowired
	public BusinessEntityServiceImp(BusinessEntityRepository beRepository) {
		this.beRepository = beRepository;
	}
	
	public Businessentity save(Businessentity be) {
		return beRepository.save(be);
	}
	
	public Iterable<Businessentity> findAll() {
		return beRepository.findAll();
	}
	
	public void delete(Businessentity be) {
		beRepository.delete(be);

	}
	
	public Optional<Businessentity> findById(long id) {

		return beRepository.findById(id);
	}

	public void update(Businessentity be, long id) {
		Businessentity modBe = beRepository.findById(id).get();
		modBe.setName(be.getName());
	
		beRepository.save(modBe);
		
	}
	

}
