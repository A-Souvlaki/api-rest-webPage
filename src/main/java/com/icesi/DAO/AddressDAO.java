package com.icesi.DAO;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.icesi.model.Address;

@Repository
@Transactional
public class AddressDAO implements Dao<Address> {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Optional<Address> get(Integer id) {
		return Optional.ofNullable(entityManager.find(Address.class, id));
	}

	@Override
	public Optional<Address> get1(long id) {
		return Optional.ofNullable(entityManager.find(Address.class, id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Address> getAll() {
		Query query = entityManager.createQuery("SELECT e FROM Address e");
        return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")

	public List<Address> getAllByID(String name) {
		Query query = entityManager.createQuery("SELECT e FROM Address e WHERE stateprovince.name= " +"\'"+name+"\'");
        return query.getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Address> getByDate(String date) {

		Query query = entityManager.createQuery("SELECT e FROM Address e WHERE modifieddate = " + "\'"+date+"\'");
        return query.getResultList();
	}

	@Override
	@Transactional
	public void save(Address t) {
	    entityManager.persist(t);
	
		
	}

	@Override
	@Transactional
	public void update(Address t) {
	    entityManager.merge(t);
		
	}	
}
