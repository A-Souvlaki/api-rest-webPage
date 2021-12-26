package com.icesi.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import com.icesi.model.Address;
import com.icesi.model.BasicInfo;
import com.icesi.model.Businessentity;
import com.icesi.model.Customer;
import com.icesi.model.Person;
import com.icesi.model.Stateprovince;
import com.icesi.model.Store;

@Component
public class DelegatedAdmin {

	@Autowired
	private RestTemplate restTemplate;

	// BUSINESS ENTITY
	// ---------------------------------------------------------------------------------

	public Businessentity getEntity(long id) {
		String url = "http://localhost:8080/entities/" + id;
		Businessentity be = restTemplate.getForObject(url, Businessentity.class);
		return be;
	}

	public Iterable<Businessentity> getAllEntities() {
		String url = "http://localhost:8080/entities";
		Businessentity[] be = restTemplate.getForObject(url, Businessentity[].class);
		return Arrays.asList(be);
	}

	public String createEntity(Businessentity be) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Businessentity> entity = new HttpEntity<Businessentity>(be, headers);

		return restTemplate.exchange("http://localhost:8080/entities", HttpMethod.POST, entity, String.class).getBody();
	}

	public String updateEntity(long id, @Validated(BasicInfo.class) Businessentity be) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Businessentity> entity = new HttpEntity<Businessentity>(be, headers);

		return restTemplate.exchange("http://localhost:8080/entities/" + id, HttpMethod.PUT, entity, String.class)
				.getBody();
	}

	// PERSON
	// ---------------------------------------------------------------------------------

	public Person getPerson(long id) {
		String url = "http://localhost:8080/persons/" + id;
		Person pe = restTemplate.getForObject(url, Person.class);
		return pe;
	}

	public Iterable<Person> getAllPersons() {
		String url = "http://localhost:8080/persons";
		Person[] pe = restTemplate.getForObject(url, Person[].class);
		return Arrays.asList(pe);
	}

	public Iterable<Person> getAllPersonsByID(long id) {
		String url = "http://localhost:8080/persons/filter=" + id;
		Person[] pe = restTemplate.getForObject(url, Person[].class);
		return Arrays.asList(pe);
	}

	public Iterable<Person> getAllPersonsByTitle(String title) {
		String url = "http://localhost:8080/persons/title=" + title;
		Person[] pe = restTemplate.getForObject(url, Person[].class);
		return Arrays.asList(pe);
	}

	public Iterable<Person> getAllPersonsByInterval(String date1, String date2) {
		String url = "http://localhost:8080/persons/date1=" + date1 + "/date2=" + date2;
		Person[] pe = restTemplate.getForObject(url, Person[].class);
		return Arrays.asList(pe);
	}

	public String createPerson(Person pe) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Person> entity = new HttpEntity<Person>(pe, headers);

		return restTemplate.exchange("http://localhost:8080/persons", HttpMethod.POST, entity, String.class).getBody();
	}

	public String updatePerson(long id, @Validated(BasicInfo.class) Person pe) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Person> entity = new HttpEntity<Person>(pe, headers);

		return restTemplate.exchange("http://localhost:8080/persons/" + id, HttpMethod.PUT, entity, String.class)
				.getBody();
	}

	// STATE PROVINCE
	// ---------------------------------------------------------------------------------

	public Stateprovince getState(Integer id) {
		String url = "http://localhost:8080/states/" + id;
		Stateprovince sp = restTemplate.getForObject(url, Stateprovince.class);
		return sp;
	}

	public Iterable<Stateprovince> getAllStates() {
		String url = "http://localhost:8080/states";
		Stateprovince[] sp = restTemplate.getForObject(url, Stateprovince[].class);
		return Arrays.asList(sp);
	}

	public String createState(Stateprovince sp) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Stateprovince> entity = new HttpEntity<Stateprovince>(sp, headers);

		return restTemplate.exchange("http://localhost:8080/states", HttpMethod.POST, entity, String.class).getBody();
	}

	public String updateState(Integer id, @Validated(BasicInfo.class) Stateprovince sp) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Stateprovince> entity = new HttpEntity<Stateprovince>(sp, headers);

		return restTemplate.exchange("http://localhost:8080/states/" + id, HttpMethod.PUT, entity, String.class)
				.getBody();
	}

	// ADRESSES
	// ---------------------------------------------------------------------------------

	public Address getAddress(Integer id) {
		String url = "http://localhost:8080/addresses/" + id;
		Address ad = restTemplate.getForObject(url, Address.class);
		return ad;
	}

	public Iterable<Address> getAllAddresses() {
		String url = "http://localhost:8080/addresses";
		Address[] ad = restTemplate.getForObject(url, Address[].class);
		return Arrays.asList(ad);
	}

	public Iterable<Address> getAllAddressesByID(Integer id) {
		String url = "http://localhost:8080/addresses/filter=" + id;
		Address[] ad = restTemplate.getForObject(url, Address[].class);
		return Arrays.asList(ad);
	}

	public Iterable<Address> getAllAddressesByDate(String date) {
		String url = "http://localhost:8080/addresses/date=" + date;
		Address[] ad = restTemplate.getForObject(url, Address[].class);
		return Arrays.asList(ad);
	}

	public String createAddresses(Address ad) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Address> entity = new HttpEntity<Address>(ad, headers);

		return restTemplate.exchange("http://localhost:8080/addresses", HttpMethod.POST, entity, String.class)
				.getBody();
	}

	public String updateAddresses(Integer id, @Validated(BasicInfo.class) Address ad) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Address> entity = new HttpEntity<Address>(ad, headers);

		return restTemplate.exchange("http://localhost:8080/addresses/" + id, HttpMethod.PUT, entity, String.class)
				.getBody();
	}

	// STORES
	// ---------------------------------------------------------------------------------

	public Store getStore(Integer id) {
		String url = "http://localhost:8080/stores/" + id;
		Store st = restTemplate.getForObject(url, Store.class);
		return st;
	}

	public String deleteStore(Integer id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Store> entity = new HttpEntity<Store>(headers);

		return restTemplate.exchange("http://localhost:8080/stores/" + id, HttpMethod.DELETE, entity, String.class)
				.getBody();
	}

	public Iterable<Store> getAllStores() {
		String url = "http://localhost:8080/stores";
		Store[] st = restTemplate.getForObject(url, Store[].class);
		return Arrays.asList(st);
	}

	public String createStore(Store st) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Store> entity = new HttpEntity<Store>(st, headers);

		return restTemplate.exchange("http://localhost:8080/stores", HttpMethod.POST, entity, String.class).getBody();
	}

	public String updateStore(Integer id, @Validated(BasicInfo.class) Store st) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Store> entity = new HttpEntity<Store>(st, headers);

		return restTemplate.exchange("http://localhost:8080/stores/" + id, HttpMethod.PUT, entity, String.class)
				.getBody();
	}

	// CUSTOMERS
	// ---------------------------------------------------------------------------------
	public Customer getCustomer(Integer id) {
		String url = "http://localhost:8080/customers/" + id;
		Customer ct = restTemplate.getForObject(url, Customer.class);
		return ct;
	}

	public Iterable<Customer> getAllCustomers() {
		String url = "http://localhost:8080/customers";
		Customer[] ct = restTemplate.getForObject(url, Customer[].class);
		return Arrays.asList(ct);
	}
	
	
	public String deleteCustomer(Integer id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Customer> entity = new HttpEntity<Customer>(headers);

		return restTemplate.exchange("http://localhost:8080/customers/" + id, HttpMethod.DELETE, entity, String.class)
				.getBody();
	}

	public Iterable<Customer> getAllCustomersByID(Integer id) {
		String url = "http://localhost:8080/customers/filter=" + id;
		Customer[] ct = restTemplate.getForObject(url, Customer[].class);
		return Arrays.asList(ct);
	}

	public Iterable<Customer> getAllCustomersByPerson(Integer id) {
		String url = "http://localhost:8080/customers/person=" + id;
		Customer[] ct = restTemplate.getForObject(url, Customer[].class);
		return Arrays.asList(ct);
	}

	public String createCustomer(Customer ct) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Customer> entity = new HttpEntity<Customer>(ct, headers);

		return restTemplate.exchange("http://localhost:8080/customers", HttpMethod.POST, entity, String.class)
				.getBody();
	}

	public String updateCustomer(Integer id, @Validated(BasicInfo.class) Customer ct) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Customer> entity = new HttpEntity<Customer>(ct, headers);

		return restTemplate.exchange("http://localhost:8080/customers/" + id, HttpMethod.PUT, entity, String.class)
				.getBody();
	}

}
