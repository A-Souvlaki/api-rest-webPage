package com.icesi.integration.test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;

import com.icesi.DAO.BusinessEntityDAO;
import com.icesi.DAO.PersonDAO;
import com.icesi.boot.AuthenticationApplication;
import com.icesi.model.Businessentity;
import com.icesi.model.Person;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = AuthenticationApplication.class)
public class PersonDAOTest {
	
	@Autowired
	private PersonDAO personRepository;
	
	@Autowired
	private BusinessEntityDAO beRepository;
	
	
	@Test
	@Order(1)
	public void shouldSavePerson() {
		Businessentity be = new Businessentity();
		be.setName("Tencet");
		beRepository.save(be);
		Person pe2 = new Person();
		pe2.setBusinessentity(beRepository.get1(2).get());
		pe2.setFirstname("Juan");
		pe2.setLastname("David");
		pe2.setTitle("Manager");
		pe2.setModifieddate(LocalDate.now());
		personRepository.save(pe2);
		List<Person> persons = personRepository.getAll(); 
		assertThat(persons.size(), equalTo(2));
	}
	
	
	@Test
	@Order(2)
	public void shouldFindByIDPerson() {
		Person peSaved = personRepository.get1(500).orElse(null);
		assertThat(peSaved, is(nullValue()));
		
	}
	
	@Test
	@Order(3)
	public void shouldGetAllPersons() {

		List<Person> persons = personRepository.getAll(); 
		assertThat(persons.size(), equalTo(1));
	}

	
	@Test
	@Order(4)
	public void shouldUpdatePerson() {
		Person pe = personRepository.get1(1).get();
		pe.setFirstname("Mario Hugo");
		personRepository.update(pe);
		
		assertThat(personRepository.get1(1).get().getFirstname(), equalTo("Mario Hugo"));
	}
	
	
	@Test
	@Order(5)
	public void shouldSearchByTitle() {
		String r = "Rioter";
		List<Person> persons = personRepository.getTittle(r); 
		assertThat(persons.size(), equalTo(1));
	}
	
	@Test
	@Order(6)
	public void shouldSearchByInter() {
		List<Person> persons = personRepository.getByInterval("2021-11-28", "2021-11-30"); 
		//Porque no se ha agregado ninguna direccion
		assertThat(persons.size(), equalTo(0));
	}

}
