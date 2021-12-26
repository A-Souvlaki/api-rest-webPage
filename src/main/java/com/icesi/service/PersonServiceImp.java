package com.icesi.service;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.model.Person;
import com.icesi.repository.BusinessEntityRepository;
import com.icesi.repository.PersonRepository;


@Service
public class PersonServiceImp {
	
	private PersonRepository personRepository;
	private BusinessEntityRepository beRepository;

	@Autowired
	public PersonServiceImp(PersonRepository personRepository,BusinessEntityRepository beRepository) {
		this.personRepository = personRepository;
		this.beRepository = beRepository;
	}
	
	public Person save(Person person) {		
		person.setBusinessentity(beRepository.getById(person.getBusinessentity().getBusinessentityid()));
		return personRepository.save(person);
	}
	
	public Iterable<Person> findAll() {
		return personRepository.findAll();
	}
	
	public void delete(Person person) {
		personRepository.delete(person);

	}
	
	public void deleteAll() {
		personRepository.deleteAll();
	}
	
	public Person findById(long id) {

		return personRepository.getById(id);
	}
	
	
	public void update(Person person) {
		Person modPerson = personRepository.getById(person.getPersonid());
		modPerson.setFirstname(person.getFirstname());
		modPerson.setLastname(person.getLastname());
		modPerson.setTitle(person.getTitle());
		modPerson.setBusinessentity(beRepository.getById(person.getBusinessentity().getBusinessentityid()));

		personRepository.save(modPerson);
	}
	
	
	public Iterable<Person> findAllById(long id) {
		ArrayList<Person> persons = (ArrayList<Person>) personRepository.findAll();
		String name = beRepository.getById(id).getName();
		ArrayList<Person> toReturn = new ArrayList<Person>();
		
		for (Person person : persons) {
			if(person.getBusinessentity().getName().equals(name)) {
				toReturn.add(person);
			}
		}
		return toReturn;
	}
	

}
