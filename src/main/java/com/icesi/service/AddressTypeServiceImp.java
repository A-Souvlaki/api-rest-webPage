package com.icesi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.model.Addresstype;
import com.icesi.repository.AddressTypeRepository;


@Service
public class AddressTypeServiceImp {
	
	private AddressTypeRepository addressTRepository;

	@Autowired
	public AddressTypeServiceImp( AddressTypeRepository addressTRepository) {
		this.addressTRepository = addressTRepository;
	}
	
	public Addresstype save(Addresstype ad) {
		return addressTRepository.save(ad);
	}
	
	public Iterable<Addresstype> findAll() {
		return addressTRepository.findAll();
	}

	public Optional<Addresstype> findById(Integer id) {
		return addressTRepository.findById(id);
	}

	public void delete(Addresstype adt) {
		addressTRepository.delete(adt);
		
	}

	public void update(Addresstype adt) {
		Addresstype modAd = addressTRepository.findById(adt.getAddresstypeid()).get();
		modAd.setName(adt.getName());
		
		
		
		
		addressTRepository.save(modAd);
		
	}

	
}
