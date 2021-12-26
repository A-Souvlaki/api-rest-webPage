package com.icesi.serviceDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.DAO.CustomerDAO;
import com.icesi.DAO.StoreDAO;
import com.icesi.model.Customer;


@Service
public class CustomerServiceDAO {
	

	private CustomerDAO customerRepository;
	
	private StoreDAO storeRepository;
	
	@Autowired
	public CustomerServiceDAO(CustomerDAO customerRepository,StoreDAO storeRepository) {
		this.customerRepository= customerRepository;
		this.storeRepository = storeRepository;
	}
	
	
	public void save(Customer st) {
		st.setStore(storeRepository.get(st.getStore().getBusinessentityid()).get());
		customerRepository.save(st);
	}
	
	public Iterable<Customer> findAll() {
		return customerRepository.getAll();
	}
	
	public Optional<Customer> findById(Integer id) {

		return customerRepository.get(id);
	}

	public void update(Customer st, Integer id) {
		Customer modSt = customerRepository.get(id).get();
		modSt.setPersonid(st.getPersonid());
		modSt.setStore(storeRepository.get(st.getStore().getBusinessentityid()).get());
	
		customerRepository.update(modSt);
		
	}


	public List<Customer> findAllById(Integer id) {
		String name = storeRepository.get(id).get().getName();
		ArrayList<Customer> customers = (ArrayList<Customer>) customerRepository.getAllByID(name);
	
		return customers;
	}


	public List<Customer> findAllByPerson(Integer id) {
		ArrayList<Customer> customers = (ArrayList<Customer>) customerRepository.getAllByPerson(id);
		
		return customers;
	}
	
	public void delete(Customer t) {
		customerRepository.delete(t);
	}

}
