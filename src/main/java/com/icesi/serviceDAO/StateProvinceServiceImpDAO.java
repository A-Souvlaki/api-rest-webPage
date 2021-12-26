package com.icesi.serviceDAO;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.DAO.StateProvinceDAO;
import com.icesi.model.Address;

import com.icesi.model.Stateprovince;

@Service
public class StateProvinceServiceImpDAO {
	
	
	private StateProvinceDAO spRepository;

	@Autowired
	public StateProvinceServiceImpDAO(StateProvinceDAO spRepository) {
		this.spRepository = spRepository;
	}
	
	public void save(Stateprovince sp) {
		spRepository.save(sp);
	}
	
	public Iterable<Stateprovince> findAll() {
		return spRepository.getAll();
	}
	
	
	public Iterable<Address> findAddresses(Integer id){
		return spRepository.get(id).get().getAddresses();
	}
	
	
	public Optional<Stateprovince> findById(Integer id) {

		return spRepository.get(id);
	}
	
	public void update(Stateprovince sp, Integer id) {
		Stateprovince modSp = spRepository.get(id).get();
		modSp.setName(sp.getName());
	
		spRepository.update(modSp);
	}
	
	
}
