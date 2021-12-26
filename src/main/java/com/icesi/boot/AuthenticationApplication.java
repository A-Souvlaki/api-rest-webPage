package com.icesi.boot;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import com.icesi.DAO.AddressDAO;
import com.icesi.DAO.BusinessEntityDAO;
import com.icesi.DAO.PersonDAO;
import com.icesi.DAO.StateProvinceDAO;
import com.icesi.model.Address;
import com.icesi.model.Addresstype;
import com.icesi.model.Businessentity;
import com.icesi.model.Person;
import com.icesi.model.Phonenumbertype;
import com.icesi.model.Stateprovince;
import com.icesi.repository.AddressTypeRepository;
import com.icesi.repository.PhoneNumberTypeRepository;


@SpringBootApplication
@EnableJpaRepositories("com.icesi.repository")
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.icesi.authentication","com.icesi.model"})
@ComponentScan(basePackages = {"com.icesi.authentication","com.icesi.repository","com.icesi.service","com.icesi.controller","com.icesi.DAO","com.icesi.serviceDAO","com.icesi.rest"})
public class AuthenticationApplication {
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
		
	}
	
	@Bean
	public CommandLineRunner add(AddressTypeRepository adtRepository, PhoneNumberTypeRepository pntRepository,BusinessEntityDAO beRepository,
			PersonDAO personRepository, StateProvinceDAO stRepository, AddressDAO adRepository) {
		return (args) -> {
			Addresstype a1 = new Addresstype();
			Addresstype a2 = new Addresstype();
			Addresstype a3 = new Addresstype();
			a1.setName("Calle Urbana");
			a2.setName("Troncal");
			a3.setName("Carreras y Avenidas");
			
			adtRepository.save(a1);
			adtRepository.save(a2);
			adtRepository.save(a3);
			
			
			Phonenumbertype p1 = new Phonenumbertype();
			Phonenumbertype p2 = new Phonenumbertype();
			p1.setName("Telefono Movil");
			p2.setName("Telefono Fijo");
			
			pntRepository.save(p1);
			pntRepository.save(p2);
			
			Businessentity b1 = new Businessentity();
			b1.setName("Riot");
			beRepository.save(b1);
			
			Person pe1 = new Person();
			pe1.setBusinessentity(b1);
			pe1.setFirstname("Cristian");
			pe1.setLastname("Camilo");
			pe1.setTitle("Rioter");
			pe1.setModifieddate(LocalDate.now());
			personRepository.save(pe1);
			
			
			Stateprovince sp = new Stateprovince();
			sp.setName("Valle del Cauca");
			stRepository.save(sp);
			
			Address ad = new Address();
			ad.setStateprovince(sp);
			ad.setAddressline1("Calle x");
			ad.setCity("Cali");
			ad.setPostalcode("52005");
			ad.setModifieddate(LocalDate.now());
			adRepository.save(ad);
			
			
		};
	}
	
	 @Bean
	 public RestTemplate getRestTemplate() {
	    return new RestTemplate();
	 }

}
