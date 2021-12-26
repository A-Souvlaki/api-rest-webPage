package com.icesi.integration.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.icesi.DAO.PersonDAO;
import com.icesi.DAO.PersonPhoneDAO;
import com.icesi.boot.AuthenticationApplication;
import com.icesi.model.Person;
import com.icesi.model.Personphone;
import com.icesi.repository.PhoneNumberTypeRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = AuthenticationApplication.class)
public class PersonPhoneDAOTest {
	
	@Autowired
	private PersonPhoneDAO phoneRepository;
	
	@Autowired
	private PersonDAO personRepository;
	
	@Autowired
	private PhoneNumberTypeRepository pntRepository;
	
	public void scenary() {
		Personphone pp = new Personphone();
		Person pe2 = new Person();
		pe2.setFirstname("Juan");
		pe2.setLastname("David");
		pe2.setTitle("Manager");
		pe2.setModifieddate(LocalDate.now());
		personRepository.save(pe2);
		
		pp.setPerson(pe2);
		pp.setPhonenumbertype(pntRepository.getById(1));
		pp.setPhone("3116438972");
		phoneRepository.save(pp);
	}
	
	@Test
	@Order(1)
	public void shouldSavePhone() {
		Personphone pp = new Personphone();
		Person pe2 = new Person();
		pe2.setFirstname("Juan");
		pe2.setLastname("David");
		pe2.setTitle("Manager");
		pe2.setModifieddate(LocalDate.now());
		personRepository.save(pe2);
		
		pp.setPerson(pe2);
		pp.setPhonenumbertype(pntRepository.getById(1));
		pp.setPhone("3116438972");
		phoneRepository.save(pp);
		List<Personphone> phones = phoneRepository.getAll(); 
		assertThat(phones.size(), equalTo(2));
	}
	
	
	@Test
	@Order(2)
	public void shouldFindByIDStateProvince() {
		Personphone ppSaved = phoneRepository.get(500).orElse(null);
		assertThat(ppSaved, is(nullValue()));
		
	}
	
	@Test
	@Order(3)
	public void shouldGetAllStateProvince() {

		List<Personphone> phones = phoneRepository.getAll(); 
		assertThat(phones.size(), equalTo(2));
	}

	
	@Test
	@Order(4)
	public void shouldUpdateBusinessentity() {
		scenary();
		Personphone pp = phoneRepository.get(1).get();
		pp.setPhone("Nada");
		phoneRepository.update(pp);
		
		assertThat(phoneRepository.get(1).get().getPhone(), equalTo("Nada"));
	}

}
