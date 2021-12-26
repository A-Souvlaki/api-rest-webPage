package com.icesi.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.icesi.model.Address;
import com.icesi.model.BasicInfo;
import com.icesi.model.Businessentity;
import com.icesi.model.Customer;
import com.icesi.model.Person;
import com.icesi.model.Stateprovince;
import com.icesi.model.Store;
import com.icesi.serviceDAO.AddressServiceDAO;
import com.icesi.serviceDAO.BusinessEntityServiceImpDAO;
import com.icesi.serviceDAO.CustomerServiceDAO;
import com.icesi.serviceDAO.PersonServiceImpDAO;
import com.icesi.serviceDAO.StateProvinceServiceImpDAO;
import com.icesi.serviceDAO.StoreServiceDAO;

@RestController
public class AdminRestController {

	PersonServiceImpDAO personService;
	BusinessEntityServiceImpDAO beService;

	StateProvinceServiceImpDAO spService;
	AddressServiceDAO adService;

	StoreServiceDAO storeService;
	CustomerServiceDAO ctService;
	
	@Autowired
	public AdminRestController(PersonServiceImpDAO personService, BusinessEntityServiceImpDAO beService,
			AddressServiceDAO adService, StateProvinceServiceImpDAO spService, StoreServiceDAO storeService,CustomerServiceDAO ctService) {
		this.personService = personService;
		this.beService = beService;
		this.adService = adService;
		this.spService = spService;
		this.storeService = storeService;
		this.ctService = ctService;
	}

	// BUSINESS ENTITY
	// ----------------------------------------------------------------------------------------------

	@RequestMapping(value = "/entities/{id}", method = RequestMethod.GET)
	public ResponseEntity<Businessentity> getEntity(@PathVariable(value = "id") long id) {
		Businessentity be = beService.findById(id).get();
		return new ResponseEntity<Businessentity>(be, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/entities", method = RequestMethod.GET)
	public ResponseEntity<Businessentity> listEntities() {
		List<Businessentity> entities = ((List<Businessentity>) beService.findAll());
		return new ResponseEntity(entities, HttpStatus.OK);
	}

	@RequestMapping(value = "/entities", method = RequestMethod.POST)
	public ResponseEntity<Businessentity> createEntity(@Validated(BasicInfo.class) @RequestBody Businessentity be) {
		beService.save(be);
		return new ResponseEntity<Businessentity>(be, HttpStatus.CREATED);
	}

	@PutMapping("/entities/{id}")
	public ResponseEntity<Businessentity> updateEntity(@PathVariable(value = "id") long id,
			@Validated(BasicInfo.class) @RequestBody Businessentity be) {

		beService.update(be, id);
		return ResponseEntity.ok(be);
	}

	// PERSON
	// ----------------------------------------------------------------------------------------------

	@RequestMapping(value = "/persons/{id}", method = RequestMethod.GET)
	public ResponseEntity<Person> getPerson(@PathVariable(value = "id") long id) {
		Person pe = personService.findById(id);
		return new ResponseEntity<Person>(pe, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public ResponseEntity<Person> listPersons() {
		List<Person> persons = ((List<Person>) personService.findAll());
		return new ResponseEntity(persons, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/persons/filter={id}", method = RequestMethod.GET)
	public ResponseEntity<Person> listPersonsByID(@PathVariable(value = "id") long id) {
		List<Person> persons = ((List<Person>) personService.findAllById(id));
		return new ResponseEntity(persons, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/persons/title={title}", method = RequestMethod.GET)
	public ResponseEntity<Person> listPersonsByTitle(@PathVariable(value = "title") String title) {
		List<Person> persons = ((List<Person>) personService.findByTittle(title));
		return new ResponseEntity(persons, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/persons/date1={date1}/date2={date2}", method = RequestMethod.GET)
	public ResponseEntity<Person> listPersonsByDate(@PathVariable(value = "date1") String date1,
			@PathVariable(value = "date2") String date2) {
		List<Person> persons = ((List<Person>) personService.findByInterval(date1, date2));
		return new ResponseEntity(persons, HttpStatus.OK);
	}

	@RequestMapping(value = "/persons", method = RequestMethod.POST)
	public ResponseEntity<Person> createPerson(@Validated(BasicInfo.class) @RequestBody Person pe) {
		personService.save(pe);
		return new ResponseEntity<Person>(pe, HttpStatus.CREATED);
	}

	@PutMapping("/persons/{id}")
	public ResponseEntity<Person> updatePerson(@Validated(BasicInfo.class) @RequestBody Person pe) {

		personService.update(pe);
		return ResponseEntity.ok(pe);
	}

	// STATES
	// ----------------------------------------------------------------------------------------------

	@RequestMapping(value = "/states/{id}", method = RequestMethod.GET)
	public ResponseEntity<Stateprovince> getState(@PathVariable(value = "id") Integer id) {
		Stateprovince sp = spService.findById(id).get();
		return new ResponseEntity<Stateprovince>(sp, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/states", method = RequestMethod.GET)
	public ResponseEntity<Stateprovince> listStates() {
		List<Stateprovince> states = ((List<Stateprovince>) spService.findAll());
		return new ResponseEntity(states, HttpStatus.OK);
	}

	@RequestMapping(value = "/states", method = RequestMethod.POST)
	public ResponseEntity<Stateprovince> createState(@Validated(BasicInfo.class) @RequestBody Stateprovince sp) {
		spService.save(sp);
		return new ResponseEntity<Stateprovince>(sp, HttpStatus.CREATED);
	}

	@PutMapping("/states/{id}")
	public ResponseEntity<Stateprovince> updateState(@PathVariable(value = "id") Integer id,
			@Validated(BasicInfo.class) @RequestBody Stateprovince sp) {
		spService.update(sp, id);
		return ResponseEntity.ok(sp);
	}

	// ADRESSESES
	// ----------------------------------------------------------------------------------------------

	@RequestMapping(value = "/addresses/{id}", method = RequestMethod.GET)
	public ResponseEntity<Address> getAddress(@PathVariable(value = "id") Integer id) {
		Address ad = adService.findById(id);
		return new ResponseEntity<Address>(ad, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/addresses", method = RequestMethod.GET)
	public ResponseEntity<Address> listAddresses() {
		List<Address> addresses = ((List<Address>) adService.findAll());
		return new ResponseEntity(addresses, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/addresses/filter={id}", method = RequestMethod.GET)
	public ResponseEntity<Address> listAddressesByID(@PathVariable(value = "id") Integer id) {
		List<Address> addresses = ((List<Address>) adService.findAllById(id));
		return new ResponseEntity(addresses, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/addresses/date={date}", method = RequestMethod.GET)
	public ResponseEntity<Address> listAddressesByID(@PathVariable(value = "date") String date) {
		List<Address> addresses = ((List<Address>) adService.findByDate(date));
		return new ResponseEntity(addresses, HttpStatus.OK);
	}

	@RequestMapping(value = "/addresses", method = RequestMethod.POST)
	public ResponseEntity<Address> createAddress(@Validated(BasicInfo.class) @RequestBody Address ad) {
		adService.save(ad);
		return new ResponseEntity<Address>(ad, HttpStatus.CREATED);
	}

	@PutMapping("/addresses/{id}")
	public ResponseEntity<Address> updateAddress(@Validated(BasicInfo.class) @RequestBody Address ad) {

		adService.update(ad);
		return ResponseEntity.ok(ad);
	}

	// STORES
	// ----------------------------------------------------------------------------------------------

	@RequestMapping(value = "/stores/{id}", method = RequestMethod.GET)
	public ResponseEntity<Store> getStore(@PathVariable(value = "id") Integer id) {
		Store st = storeService.findById(id).get();
		return new ResponseEntity<Store>(st, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/stores/{id}", method = RequestMethod.DELETE)
	public void delStore(@PathVariable(value = "id") Integer id) {
		Store st = storeService.findById(id).get();
		storeService.delete(st);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/stores", method = RequestMethod.GET)
	public ResponseEntity<Store> listStores() {
		List<Store> entities = ((List<Store>) storeService.findAll());
		return new ResponseEntity(entities, HttpStatus.OK);
	}

	@RequestMapping(value = "/stores", method = RequestMethod.POST)
	public ResponseEntity<Store> createStore(@Validated(BasicInfo.class) @RequestBody Store st) {
		storeService.save(st);
		return new ResponseEntity<Store>(st, HttpStatus.CREATED);
	}

	@PutMapping("/stores/{id}")
	public ResponseEntity<Store> updateStore(@PathVariable(value = "id") Integer id,
			@Validated(BasicInfo.class) @RequestBody Store st) {

		storeService.update(st, id);
		return ResponseEntity.ok(st);
	}

	// CLIENTS
	// ----------------------------------------------------------------------------------------------
	@RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)
	public ResponseEntity<Customer> getCustomer(@PathVariable(value = "id") Integer id) {
		Customer ct = ctService.findById(id).get();
		return new ResponseEntity<Customer>(ct, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	public ResponseEntity<Customer> listCustomers() {
		List<Customer> ct = ((List<Customer>) ctService.findAll());
		return new ResponseEntity(ct, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE)
	public void delCustomer(@PathVariable(value = "id") Integer id) {
		Customer ct = ctService.findById(id).get();
		ctService.delete(ct);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/customers/filter={id}", method = RequestMethod.GET)
	public ResponseEntity<Customer> listCustomerByID(@PathVariable(value = "id") Integer id) {
		List<Customer> ct = ((List<Customer>) ctService.findAllById(id));
		return new ResponseEntity(ct, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/customers/person={id}", method = RequestMethod.GET)
	public ResponseEntity<Customer> listCustomerByPerson(@PathVariable(value = "id") Integer id) {
		List<Customer> ct = ((List<Customer>) ctService.findAllByPerson(id));
		return new ResponseEntity(ct, HttpStatus.OK);
	}

	@RequestMapping(value = "/customers", method = RequestMethod.POST)
	public ResponseEntity<Customer> createCustomer(@Validated(BasicInfo.class) @RequestBody Customer ct) {
		ctService.save(ct);
		return new ResponseEntity<Customer>(ct, HttpStatus.CREATED);
	}

	@PutMapping("/customers/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") Integer id, @Validated(BasicInfo.class) @RequestBody Customer ct) {

		ctService.update(ct,id);
		return ResponseEntity.ok(ct);
	}

}
