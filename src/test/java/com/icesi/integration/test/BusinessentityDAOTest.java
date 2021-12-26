package com.icesi.integration.test;
import static org.hamcrest.CoreMatchers.nullValue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.icesi.DAO.BusinessEntityDAO;
import com.icesi.boot.AuthenticationApplication;
import com.icesi.model.Businessentity;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = AuthenticationApplication.class)
public class BusinessentityDAOTest {
	
	@Autowired
	private BusinessEntityDAO beRepository;
	
	@Test
	public void shouldSaveBusinessentity() {
		Businessentity be = new Businessentity();
		be.setName("Tencet");
		beRepository.save(be);
		List<Businessentity> entities = beRepository.getAll(); 
		assertThat(entities.size(), equalTo(2));
	}
	
	
	@Test
	public void shouldFindByIDBusinessentity() {
		Businessentity beSaved = beRepository.get1(500).orElse(null);
		assertThat(beSaved, is(nullValue()));
		
	}
	
	@Test
	public void shouldGetAllBusinessentity() {

		List<Businessentity> entities = beRepository.getAll(); 
		assertThat(entities.size(), equalTo(2));
	}

	
	@Test
	public void shouldUpdateBusinessentity() {
		Businessentity be = beRepository.get1(1).get();
		be.setName("RiotGames");
		beRepository.update(be);
		
		assertThat(beRepository.get1(1).get().getName(), equalTo("RiotGames"));
	}

}
