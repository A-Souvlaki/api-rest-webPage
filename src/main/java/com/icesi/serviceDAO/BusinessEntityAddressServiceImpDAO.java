package com.icesi.serviceDAO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.DAO.AddressDAO;
import com.icesi.DAO.BusinessEntityAddressDAO;
import com.icesi.DAO.BusinessEntityDAO;
import com.icesi.DAO.PersonDAO;
import com.icesi.model.Businessentityaddress;
import com.icesi.model.Person;
import com.icesi.repository.AddressTypeRepository;


@Service
public class BusinessEntityAddressServiceImpDAO {
	
	private BusinessEntityAddressDAO beaRepository;
	private BusinessEntityDAO beRepository;
	private AddressTypeRepository adtRepository;
	private AddressDAO adRepository;
	private PersonDAO personRepository;

	
	@Autowired
	public BusinessEntityAddressServiceImpDAO(BusinessEntityAddressDAO beaRepository,BusinessEntityDAO beRepository,AddressTypeRepository adtRepository,
			AddressDAO adRepository,PersonDAO personRepository) {
		this.beaRepository = beaRepository;
		this.beRepository = beRepository;
		this.adtRepository = adtRepository;
		this.adRepository = adRepository;
		this.personRepository = personRepository;
		
	}
	
	
	public void save(Businessentityaddress bea) {
		bea.setBusinessentity(beRepository.get1(bea.getBusinessentity().getBusinessentityid()).get());
		bea.setAddresstype(adtRepository.getById(bea.getAddresstype().getAddresstypeid()));
		bea.setAddress(adRepository.get(bea.getAddress().getAddressid()).get());
		
		Person modP = personRepository.get1(bea.getBusinessentity().getBusinessentityid()).get();
		
	
		beaRepository.save(bea);
		modP.incrementCounter();
		personRepository.update(modP);
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
		
		beaRepository.update(modBea);
		
	}

}
