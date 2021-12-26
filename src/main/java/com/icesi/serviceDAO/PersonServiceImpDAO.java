package com.icesi.serviceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.DAO.BusinessEntityDAO;
import com.icesi.DAO.PersonDAO;
import com.icesi.model.Person;



@Service
public class PersonServiceImpDAO {
	
	private PersonDAO personRepository;
	private BusinessEntityDAO beRepository;

	@Autowired
	public PersonServiceImpDAO(PersonDAO personRepository,BusinessEntityDAO beRepository) {
		this.personRepository = personRepository;
		this.beRepository = beRepository;
	}
	
	public void save(Person person) {		
		person.setBusinessentity(beRepository.get1(person.getBusinessentity().getBusinessentityid()).get());
		personRepository.save(person);
	}
	
	public Iterable<Person> findAll() {
		return personRepository.getAll();
	}
	
	public Iterable<Person> findByTittle(String title) {
		return personRepository.getTittle(title);
	}
	
	public Iterable<Person> findByInterval(String date1, String date2) {
		return personRepository.getByInterval(date1, date2);
	}

	
	public Person findById(long id) {

		return personRepository.get1(id).get();
	}
	
	
	public void update(Person person) {
		Person modPerson = personRepository.get1(person.getPersonid()).get();
		modPerson.setFirstname(person.getFirstname());
		modPerson.setLastname(person.getLastname());
		modPerson.setTitle(person.getTitle());
		modPerson.setBusinessentity(beRepository.get1(person.getBusinessentity().getBusinessentityid()).get());

		personRepository.update(modPerson);
	}
	
	
	public Iterable<Person> findAllById(long id) {
		return personRepository.getAllByID(id);
	}
	

}
