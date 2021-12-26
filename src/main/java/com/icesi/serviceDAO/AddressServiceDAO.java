package com.icesi.serviceDAO;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.DAO.AddressDAO;
import com.icesi.DAO.StateProvinceDAO;
import com.icesi.model.Address;

@Service
public class AddressServiceDAO {
	
	private AddressDAO addressRepository;
	private StateProvinceDAO spRepository;

	@Autowired
	public AddressServiceDAO(AddressDAO addressRepository,StateProvinceDAO spRepository) {
		this.addressRepository = addressRepository;
		this.spRepository = spRepository;
	}
	
	public void save(Address ad) {
		ad.setStateprovince(spRepository.get(ad.getStateprovince().getStateprovinceid()).get());
		addressRepository.save(ad);
	}
	
	public Iterable<Address> findAll() {
		return addressRepository.getAll();
	}
	
	public Address findById(Integer id) {

		return addressRepository.get(id).get();
	}
	
	public Iterable<Address> findByDate(String date) {

		return addressRepository.getByDate(date);
	}
	

	public void update(Address ad) {
		Address modAd = addressRepository.get(ad.getAddressid()).get();
		modAd.setAddressline1(ad.getAddressline1());
		modAd.setCity(ad.getCity());
		modAd.setPostalcode(ad.getPostalcode());
		modAd.setStateprovince(spRepository.get(ad.getStateprovince().getStateprovinceid()).get());
		
		
		
		addressRepository.update(modAd);
	}
	
	
	public Iterable<Address> findAllById(Integer id) {
		String name = spRepository.get(id).get().getName();
		ArrayList<Address> addresses = (ArrayList<Address>) addressRepository.getAllByID(name);
	
		return addresses;
	}
	
	
	
}
