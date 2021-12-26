package com.icesi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.model.Phonenumbertype;
import com.icesi.repository.PhoneNumberTypeRepository;

@Service
public class PhoneNumbertypeServiceImp {
	
	
	private PhoneNumberTypeRepository pntRepository;
	
	@Autowired
	public PhoneNumbertypeServiceImp(PhoneNumberTypeRepository pntRepository) {
		this.pntRepository = pntRepository;
	}
	
	
	public Iterable<Phonenumbertype> findAll() {
		return pntRepository.findAll();
	}
	
	public Phonenumbertype findById(Integer id) {
		return pntRepository.getById(id);
	}
	
	
}
