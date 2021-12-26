package com.icesi.integration.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.icesi.DAO.AddressDAO;
import com.icesi.DAO.BusinessEntityAddressDAO;
import com.icesi.DAO.BusinessEntityDAO;
import com.icesi.boot.AuthenticationApplication;
import com.icesi.model.Address;
import com.icesi.model.Businessentity;
import com.icesi.model.Businessentityaddress;
import com.icesi.repository.AddressTypeRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = AuthenticationApplication.class)
public class BusinessAddressTypeDAOTest {
	
	
	@Autowired
	private BusinessEntityAddressDAO beaRepository;
	
	@Autowired
	private BusinessEntityDAO beRepository;
	
	@Autowired
	private AddressDAO adRepository;
	
	@Autowired
	private AddressTypeRepository adtRepository;
	
	public void scenary() {
		Businessentityaddress bea = new Businessentityaddress();
		Address ad = new Address();
		ad.setAddressline1("Calle xyy");
		ad.setCity("Cali");
		ad.setPostalcode("52005");
		adRepository.save(ad);
		bea.setAddress(ad);
		Businessentity be = new Businessentity();
		be.setName("Tencent");
		beRepository.save(be);
		bea.setBusinessentity(be);
		bea.setAddresstype(adtRepository.getById(1));
		beaRepository.save(bea);
	}
	
	@Test
	public void shouldSaveBusinessentity() {
		Businessentityaddress bea = new Businessentityaddress();
		Address ad = new Address();
		ad.setAddressline1("Calle xyy");
		ad.setCity("Cali");
		ad.setPostalcode("52005");
		adRepository.save(ad);
		bea.setAddress(ad);
		Businessentity be = new Businessentity();
		be.setName("Tencent");
		beRepository.save(be);
		bea.setBusinessentity(be);
		bea.setAddresstype(adtRepository.getById(1));
		beaRepository.save(bea);
		List<Businessentityaddress> entities = beaRepository.getAll(); 
		assertThat(entities.size(), equalTo(1));
	}
	
	
	@Test
	public void shouldFindByIDBusinessentityAddress() {
		Businessentityaddress beaSaved = beaRepository.get(500).orElse(null);
		assertThat(beaSaved, is(nullValue()));
		
	}
	
	@Test
	public void shouldGetAllBusinessentityAddress() {
		scenary();
		List<Businessentityaddress> entities = beaRepository.getAll(); 
		assertThat(entities.size(), equalTo(2));
	}

	
	@Test
	public void shouldUpdateBusinessentityAddress() {
		scenary();
		Businessentityaddress bea = beaRepository.get(1).get();
		Address ad1 = new Address();
		ad1.setAddressline1("Calle xy");
		ad1.setCity("Popayan");
		ad1.setPostalcode("52005");
		adRepository.save(ad1);
		bea.setAddress(ad1);
		beaRepository.update(bea);
		
		assertThat(beaRepository.get(1).get().getAddress().getCity(), equalTo("Popayan"));
	}
	
}
