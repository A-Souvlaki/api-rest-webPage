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

import com.icesi.DAO.AddressDAO;
import com.icesi.DAO.StateProvinceDAO;
import com.icesi.boot.AuthenticationApplication;
import com.icesi.model.Address;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = AuthenticationApplication.class)
public class AddressDAOTest {
	
	@Autowired
	private AddressDAO adRepository;
	
	@Autowired
	private StateProvinceDAO spRepository;
	@Test
	@Order(1)
	public void shouldSaveAddress() {
		Address ad = new Address();
		ad.setStateprovince(spRepository.get(1).get());
		ad.setAddressline1("Calle xyy");
		ad.setCity("Cali");
		ad.setPostalcode("52005");
		ad.setModifieddate(LocalDate.now());
		adRepository.save(ad);
		List<Address> addresses = adRepository.getAll(); 
		assertThat(addresses.size(), equalTo(2));
	}
	
	
	@Test
	@Order(2)
	public void shouldFindByIDAddress() {
		Address adSaved = adRepository.get(500).orElse(null);
		assertThat(adSaved, is(nullValue()));
		
	}
	
	@Test
	@Order(3)
	public void shouldGetAllAddresses() {

		List<Address> addresses = adRepository.getAll(); 
		assertThat(addresses.size(), equalTo(1));
	}

	
	@Test
	@Order(4)
	public void shouldUpdateAddress() {
		Address ad = adRepository.get(1).get();
		ad.setCity("Popayan");
		adRepository.update(ad);
		
		assertThat(adRepository.get(1).get().getCity(), equalTo("Popayan"));
	}
	
	@Test
	@Order(5)
	public void shouldFindByDate() {
		List<Address> addresses = adRepository.getByDate("2021-11-29"); 
		assertThat(addresses.size(), equalTo(1));
	}
	
	
}
