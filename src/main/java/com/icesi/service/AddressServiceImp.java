package com.icesi.service;


import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.model.Address;
import com.icesi.repository.AddressRepository;
import com.icesi.repository.StateProvinceRepository;

@Service
public class AddressServiceImp {
	
	private AddressRepository addressRepository;
	private StateProvinceRepository spRepository;

	@Autowired
	public AddressServiceImp(AddressRepository addressRepository,StateProvinceRepository spRepository) {
		this.addressRepository = addressRepository;
		this.spRepository = spRepository;
	}
	
	public Address save(Address ad) {
		ad.setStateprovince(spRepository.getById(ad.getStateprovince().getStateprovinceid()));
		return addressRepository.save(ad);
	}
	
	public Iterable<Address> findAll() {
		return addressRepository.findAll();
	}
	
	public Optional<Address> findById(Integer id) {

		return addressRepository.findById(id);
	}
	
	public void delete(Address address) {
		addressRepository.delete(address);
	}
	
	public void update(Address ad) {
		Address modAd = addressRepository.findById(ad.getAddressid()).get();
		modAd.setAddressline1(ad.getAddressline1());
		modAd.setCity(ad.getCity());
		modAd.setPostalcode(ad.getPostalcode());
		modAd.setStateprovince(spRepository.getById(ad.getStateprovince().getStateprovinceid()));
		
		
		
		addressRepository.save(modAd);
	}
	
	
	public Iterable<Address> findAllById(Integer id) {
		ArrayList<Address> addresses = (ArrayList<Address>) addressRepository.findAll();
		String name = spRepository.getById(id).getName();
		ArrayList<Address> toReturn = new ArrayList<Address>();
		
		for (Address ad : addresses) {
			if(ad.getStateprovince().getName().equals(name)) {
				toReturn.add(ad);
			}
		}
		return toReturn;
	}
	
	
	
}
