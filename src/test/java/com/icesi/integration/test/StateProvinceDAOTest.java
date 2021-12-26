package com.icesi.integration.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.icesi.DAO.StateProvinceDAO;
import com.icesi.boot.AuthenticationApplication;
import com.icesi.model.Stateprovince;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = AuthenticationApplication.class)
public class StateProvinceDAOTest {
	
	@Autowired
	private StateProvinceDAO spRepository;
	
	@Test
	@Order(1)
	public void shouldSaveStateProvince() {
		Stateprovince sp = new Stateprovince();
		sp.setName("Nariño");
		spRepository.save(sp);
		List<Stateprovince> states = spRepository.getAll(); 
		assertThat(states.size(), equalTo(2));
	}
	
	
	@Test
	@Order(2)
	public void shouldFindByIDStateProvince() {
		Stateprovince spSaved = spRepository.get(500).orElse(null);
		assertThat(spSaved, is(nullValue()));
		
	}
	
	@Test
	@Order(3)
	public void shouldGetAllStateProvince() {

		List<Stateprovince> states = spRepository.getAll(); 
		assertThat(states.size(), equalTo(1));
	}

	
	@Test
	@Order(4)
	public void shouldUpdateBusinessentity() {
		Stateprovince sp = spRepository.get(1).get();
		sp.setName("Cauca");
		spRepository.update(sp);
		
		assertThat(spRepository.get(1).get().getName(), equalTo("Cauca"));
	}

}
