package com.icesi.serviceDAO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.DAO.StoreDAO;
import com.icesi.model.Store;

@Service
public class StoreServiceDAO {
	
	@Autowired
	private StoreDAO storeRepository;
	
	
	public void save(Store st) {
		storeRepository.save(st);
	}
	
	public Iterable<Store> findAll() {
		return storeRepository.getAll();
	}
	
	public Optional<Store> findById(Integer id) {

		return storeRepository.get(id);
	}

	public void update(Store st, Integer id) {
		Store modSt = storeRepository.get(id).get();
		modSt.setName(st.getName());
	
		storeRepository.update(modSt);
		
	}
	
	public void delete(Store st) {
		storeRepository.delete(st);
	}

}
