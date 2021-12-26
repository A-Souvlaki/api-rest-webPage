package com.icesi.serviceDAO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.DAO.PersonDAO;
import com.icesi.DAO.PersonPhoneDAO;
import com.icesi.model.Person;
import com.icesi.model.Personphone;
import com.icesi.repository.PhoneNumberTypeRepository;


@Service
public class PhoneServiceImpDAO {

   
    private PersonPhoneDAO phoneRepository;
    private PhoneNumberTypeRepository pntRepository;
    private PersonDAO personRepository;
    
    
    @Autowired
    public PhoneServiceImpDAO(PersonPhoneDAO phoneRepository,PhoneNumberTypeRepository pntRepository,PersonDAO personRepository) {
    	this.phoneRepository = phoneRepository;
    	this.pntRepository = pntRepository;
    	this.personRepository = personRepository;
    }
    
   
    public Optional<Personphone> findById(Integer id) {

		return phoneRepository.get(id);
	}
    
    
    public Iterable<Personphone> findAll(){
    	return phoneRepository.getAll();
    }
    
    public Iterable<Personphone> findAllTypes(){
    	return phoneRepository.getAllTypes();
    }
    
    public Iterable<Personphone> findByPref(String pref){
    	return phoneRepository.getByPref(pref);
    }
    
    public Iterable<Personphone> findByType(String type){
    	return phoneRepository.getByType(type);
    }

	public void save(Personphone phone) {
		phone.setPhonenumbertype(pntRepository.getById(phone.getPhonenumbertype().getPhonenumbertypeid()));
		phone.setType(phone.getPhonenumbertype().getName());
		
		Person modP = personRepository.get1(phone.getPerson().getPersonid()).get(); 
		
		if(phone.getPhonenumbertype().getPhonenumbertypeid() == 1){
			modP.increment1();
		}else if(phone.getPhonenumbertype().getPhonenumbertypeid() == 2){
			modP.increment2();
		}
		personRepository.update(modP);
		
		phone.setPerson(personRepository.get1(phone.getPerson().getPersonid()).get());
		
		phone.setType(phone.getPhonenumbertype().getName());
		phoneRepository.save(phone);
		
	}


	public void update(Personphone phone) {
		Personphone modPhone = phoneRepository.get(phone.getId()).get();
		modPhone.setPerson(phone.getPerson());
		modPhone.setPhonenumbertype(phone.getPhonenumbertype());
		modPhone.setPhone(phone.getPhone());

		phoneRepository.update(phone);
		
	}
    	
}

