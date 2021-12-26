package com.icesi.DAO;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.icesi.model.Customer;

@Repository
@Transactional
public class CustomerDAO implements Dao<Customer>{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Optional<Customer> get(Integer id) {
		return Optional.ofNullable(entityManager.find(Customer.class, id));
	}

	@Override
	public Optional<Customer> get1(long id) {
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getAll() {
		Query query = entityManager.createQuery("SELECT e FROM Customer e");
        return query.getResultList();
	}

	@Override
	public void save(Customer t) {
		entityManager.persist(t);
		
	}

	@Override
	public void update(Customer t) {
		entityManager.merge(t);
		
	}

	@SuppressWarnings("unchecked")
	public List<Customer> getAllByID(String name) {
		Query query = entityManager.createQuery("SELECT e FROM Customer e WHERE store.name= " +"\'"+name+"\'");
        return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Customer> getAllByPerson(Integer id ) {
		Query query = entityManager.createQuery("SELECT e FROM Customer e WHERE personid= " +id);
        return query.getResultList();
	}
	

	public void delete(Customer t) {
		entityManager.remove(t);
		
	}
	

}
