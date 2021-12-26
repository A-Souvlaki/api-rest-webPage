package com.icesi.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.model.Address;

import com.icesi.model.Stateprovince;
import com.icesi.repository.StateProvinceRepository;

@Service
public class StateProvinceServiceImp {
	
	
	private StateProvinceRepository spRepository;

	@Autowired
	public StateProvinceServiceImp(StateProvinceRepository spRepository) {
		this.spRepository = spRepository;
	}
	
	public Stateprovince save(Stateprovince sp) {
		return spRepository.save(sp);
	}
	
	public Iterable<Stateprovince> findAll() {
		return spRepository.findAll();
	}
	
	
	public Iterable<Address> findAddresses(Integer id){
		return spRepository.getById(id).getAddresses();
	}
	
	public void delete(Stateprovince sp) {
		spRepository.delete(sp);

	}
	
	public void deleteAll() {
		spRepository.deleteAll();
	}
	
	public Optional<Stateprovince> findById(Integer id) {

		return spRepository.findById(id);
	}
	
	public void update(Stateprovince sp, Integer id) {
		Stateprovince modSp = spRepository.findById(id).get();
		modSp.setName(sp.getName());
	
		spRepository.save(modSp);
	}
	
	
}
