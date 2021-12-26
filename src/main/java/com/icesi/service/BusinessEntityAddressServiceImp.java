package com.icesi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.DAO.AddressDAO;
import com.icesi.DAO.BusinessEntityAddressDAO;
import com.icesi.DAO.BusinessEntityDAO;
import com.icesi.model.Businessentityaddress;
import com.icesi.repository.AddressTypeRepository;


@Service
public class BusinessEntityAddressServiceImp {
	
	private BusinessEntityAddressDAO beaRepository;
	private BusinessEntityDAO beRepository;
	private AddressTypeRepository adtRepository;
	private AddressDAO adRepository;
	
	@Autowired
	public BusinessEntityAddressServiceImp(BusinessEntityAddressDAO beaRepository,BusinessEntityDAO beRepository,AddressTypeRepository adtRepository,
			AddressDAO adRepository) {
		this.beaRepository = beaRepository;
		this.beRepository = beRepository;
		this.adtRepository = adtRepository;
		this.adRepository = adRepository;
	}
	
	
	public void save(Businessentityaddress bea) {
		bea.setBusinessentity(beRepository.get1(bea.getBusinessentity().getBusinessentityid()).get());
		//bea.setAddresstype(adtRepository.getById(bea.getAddresstype().getAddresstypeid()));
		bea.setAddress(adRepository.get(bea.getAddress().getAddressid()).get());
		beaRepository.save(bea);
	}
	
	public Iterable<Businessentityaddress> findAll(){
		return beaRepository.getAll();
	}


	public Optional<Businessentityaddress> findById(Integer id) {
		return beaRepository.get(id);
	}



	
	public void update(Businessentityaddress bea) {
		Businessentityaddress modBea = beaRepository.get(bea.getId()).get();
		modBea.setBusinessentity(beRepository.get1(bea.getBusinessentity().getBusinessentityid()).get());
		modBea.setAddresstype(adtRepository.getById(bea.getAddresstype().getAddresstypeid()));
		modBea.setAddress(adRepository.get(bea.getAddress().getAddressid()).get());
		
		beaRepository.save(modBea);
		
	}

}
